package sistemaTestLinguistico.domain.controller;

import java.util.List;

import sistemaTestLinguistico.domain.server.model.Inventario;
import sistemaTestLinguistico.domain.server.model.Test;
import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class SomministrazioneTestController {
	private Inventario inventario;
	private Soggetto soggetto;
	
	
	public SomministrazioneTestController(Inventario inventario) {
		super();
		this.inventario = inventario;
	}
	
	public void somministraTest() {
		//scelgo il test da somministrare
		LinguisticTestsTUI.stampa("Quale test clinico vuoi somministrare?");
		inventario.visualizzaTestDefinitivi();
		int numeroTest = LinguisticTestsTUI.leggiIntero();
		List<Test> tests = inventario.getTestsDefinitivi();
		
		if (numeroTest <= tests.size() && numeroTest > 0) {
			Test testDaErogare = tests.get(numeroTest-1);
			LinguisticTestsTUI.stampa("Hai scelto di somministrare il test \"" + testDaErogare.getNome() + "\" per il disturbo \"" + testDaErogare.getDisturbo() + "\"");
			//chiedo i dati del soggetto
			soggetto = richiediDatiSoggetto();
			
			LinguisticTestsTUI.stampa("Ciao " + soggetto.getNome() + "!");
			LinguisticTestsTUI.stampa("Preparati a svolgere il test");
			LinguisticTestsTUI.stampa("Test in preparazione...");
			LinguisticTestsTUI.stampaNewLine();
			LinguisticTestsTUI.stampa("Inizio del test");
			
			EsecuzioneTest esecuzioneTest = new EsecuzioneTest(testDaErogare, soggetto);
			esecuzioneTest.avviaTest();
		} else {
			LinguisticTestsTUI.stampa("Errore: Test selezionato non valido");
		}
		
	}

	private Soggetto richiediDatiSoggetto() {
		LinguisticTestsTUI.stampa("Inserisci il nome del soggetto:");
		String nome = LinguisticTestsTUI.leggiStringa();
		LinguisticTestsTUI.stampa("Inserisci il cognome del soggetto:");
		String cognome = LinguisticTestsTUI.leggiStringa();
		LinguisticTestsTUI.stampa("Inserisci l'età del soggetto:");
		int eta = LinguisticTestsTUI.leggiIntero();
		
		return new Soggetto(nome, cognome, eta);
	}
}
