package sistemaTestLinguistico.domain.controller;
import java.util.List;
import java.util.Scanner;

import sistemaTestLinguistico.domain.server.model.Identity;
import sistemaTestLinguistico.domain.server.model.Inventario;
import sistemaTestLinguistico.domain.server.model.Test;
import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class SystemController {
	private Inventario inventario;
	private CreazioneTestPilotaController creazioneNuovoTestController;
	private ModificaTestPilotaController modificaTestPilotaController;
	private SomministrazioneTestController somministrazioneTestController;
	private CreazioneTestDefinitivoController creazioneTestDefinitivoController;
	
	//costruttore
	public SystemController() {
		super();
		inventario = new Inventario();
		creazioneNuovoTestController = new CreazioneTestPilotaController();
		modificaTestPilotaController = new ModificaTestPilotaController(inventario);
		somministrazioneTestController = new SomministrazioneTestController(inventario);
		creazioneTestDefinitivoController = new CreazioneTestDefinitivoController(inventario);
	}
	
	//Il login restituisce un oggetto Identity se ha successo
	public Identity login(String username, String password) {
		return inventario.login(username, password);
	}
	
	public Inventario getInventario() {
		return inventario;
	}
	
	public void eseguiOperazioneIniziale(Identity identity, int codiceOperazione) {
		
		switch(codiceOperazione) {
			case 1:
				if (identity.getRuolo().toLowerCase().equals("esperto")) {
					//creo test pilota se sono un esperto
					LinguisticTestsTUI.stampa("Hai scelto di creare un nuovo test pilota.");
					Test nuovoTest = creazioneNuovoTestController.creaTestPilota();
					inventario.salvaNuovoTestPilota(nuovoTest);
				} else 
					LinguisticTestsTUI.stampa("Errore: non hai il permesso di effettuare questa operazione. Riprova");
				break;
				
			case 2:
				if (identity.getRuolo().toLowerCase().equals("esperto")) {
					//creo test definitivo
					creazioneTestDefinitivoController.creaTestDefinitivo();
				} else 
					LinguisticTestsTUI.stampa("Errore: non hai il permesso di effettuare questa operazione. Riprova");
				break;
				
			case 3:
				if (identity.getRuolo().toLowerCase().equals("esperto")) {
					//modifico un test pilota
					LinguisticTestsTUI.stampa("Hai scelto di modificare un test pilota.");
					LinguisticTestsTUI.stampa("Quale dei seguenti test desideri modificare? (digita un intero)");
					
					//mostro i test disponibili da modificare
					List<Test> testsPilota = inventario.getTestsPilota();
					
					if (testsPilota.size() == 0) {
						LinguisticTestsTUI.stampa("Nessun test presente da modificare.");
						break;
					}
					//stampo i test con un ciclo
					for (int i = 0; i < testsPilota.size(); i++) {
						LinguisticTestsTUI.stampa(i+1 + ") " + testsPilota.get(i).getNome());
					}
					int indiceTest = LinguisticTestsTUI.leggiIntero();
					if (indiceTest <= testsPilota.size() && indiceTest > 0)
						modificaTestPilotaController.modificaTestPilota(testsPilota.get(indiceTest-1));
					else 
						LinguisticTestsTUI.stampa("Errore: Numero inserito non valido.");
				} else 
					LinguisticTestsTUI.stampa("Errore: non hai il permesso di effettuare questa operazione. Riprova");
				break;
				
			case 4: //Somministro il test
				//Tutti possono somministrare un test, quindi niente controlli
				LinguisticTestsTUI.stampa("Hai scelto di somministrare un test.");
				somministrazioneTestController.somministraTest();
				break;
			default:
				LinguisticTestsTUI.stampa("Il codice inserito non è valido. Riprova");
		}
	}
	

	
}
