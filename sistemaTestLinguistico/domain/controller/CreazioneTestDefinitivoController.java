package sistemaTestLinguistico.domain.controller;

import java.util.List;

import sistemaTestLinguistico.domain.server.model.Inventario;
import sistemaTestLinguistico.domain.server.model.Test;
import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class CreazioneTestDefinitivoController {
	private Inventario inventario;
	
	public CreazioneTestDefinitivoController(Inventario inventario) {
		super();
		this.inventario = inventario;
	}

	public void creaTestDefinitivo() {
		LinguisticTestsTUI.stampa("Quale test pilota vuoi marcare come definitivo?");
		inventario.visualizzaTestPilota();
		int numeroTest = LinguisticTestsTUI.leggiIntero();
		marcaTestComeDefinitivo(numeroTest-1);
	}

	private void marcaTestComeDefinitivo(int indiceTest) {
		List<Test> testsPilota = inventario.getTestsPilota();
		List<Test> testsDefinitivi = inventario.getTestsDefinitivi();
		
		if (indiceTest < testsPilota.size() && indiceTest >= 0) {
			Test test = testsPilota.get(indiceTest);
			test.setDefinitivo(true);
			testsPilota.remove(test);
			testsDefinitivi.add(test);
			LinguisticTestsTUI.stampa("Test definitivo creato con successo!");
		} else {
			LinguisticTestsTUI.stampa("Errore: test selezionato non valido.");
		}
	}
}
