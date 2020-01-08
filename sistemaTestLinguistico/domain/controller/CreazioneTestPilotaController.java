package sistemaTestLinguistico.domain.controller;

import sistemaTestLinguistico.domain.server.model.Test;
import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class CreazioneTestPilotaController {
	
	
	public CreazioneTestPilotaController() {
		super();
	}
	
	public Test creaTestPilota() {
		boolean done = false;
		LinguisticTestsTUI.stampa("Inserisci il nome del test:");
		String nomeTest = LinguisticTestsTUI.leggiStringa();
		LinguisticTestsTUI.stampa("Inserisci il disturbo per il quale vuoi creare il test:");
		String nomeDisturbo = LinguisticTestsTUI.leggiStringa();
		
		Test test = new Test(nomeTest, nomeDisturbo);
		LinguisticTestsTUI.stampa("Nuovo test pilota creato con successo!");
		return test;
	}


}
