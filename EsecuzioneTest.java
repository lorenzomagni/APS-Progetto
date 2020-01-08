package sistemaTestLinguistico.domain.controller;

import java.util.ArrayList;
import java.util.List;

import sistemaTestLinguistico.domain.server.model.Domanda;
import sistemaTestLinguistico.domain.server.model.Sezione;
import sistemaTestLinguistico.domain.server.model.Test;
import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class EsecuzioneTest {
	private Test test;
	private Cronometro cronometro;
	private String valutazione;
	private Soggetto soggetto;
	
	public EsecuzioneTest(Test test, Soggetto soggetto) {
		super();
		this.test = test;
		this.soggetto = soggetto;
	}
	
	public void avviaTest() {
		List<Sezione> sezioni = test.getSezioni();
		List<Domanda> domande = new ArrayList<Domanda>();
		int indiceDomande = 0;
		boolean termina = false;
		
		//Recupero tutte le domande di tutte le sezioni
		for (int i = 0; i < sezioni.size(); i++) {
			List<Domanda> domandeDellaSezione = sezioni.get(i).getDomande();
			for(int k = 0; k < domandeDellaSezione.size(); k++) {
				domande.add(domandeDellaSezione.get(k));
			}	
		}
		
		while (indiceDomande < domande.size() || !termina) {
			// se la slide della domanda ha una casella di testo posso rispondere alla domanda
			if (domande.get(indiceDomande).getSlide().contieneCasellaTesto()) {
				mostraDomanda(domande, indiceDomande);
				rispondiDomanda(domande.get(indiceDomande));
			}
			indiceDomande++;
		}
		
		LinguisticTestsTUI.stampa("Hai completato il test!");
		LinguisticTestsTUI.stampa("Riconsegna il tablet al somministratore");
	}
	
	// Precondizione: la domanda ha come input solo caselle di testo
	private void rispondiDomanda(Domanda domanda) {
		LinguisticTestsTUI.stampa("Inserisci risposta:");
		String risposta = LinguisticTestsTUI.leggiStringaConSpazi();
		domanda.setRisposta(risposta);
	}

	private void mostraDomanda(List<Domanda> domande, int indiceDomande) {
		LinguisticTestsTUI.stampa("Domanda:");
		LinguisticTestsTUI.stampa(indiceDomande+1 + ") " + domande.get(indiceDomande).getDomanda());	
	}

}
