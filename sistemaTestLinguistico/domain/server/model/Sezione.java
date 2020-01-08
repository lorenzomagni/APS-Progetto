package sistemaTestLinguistico.domain.server.model;

import java.util.ArrayList;
import java.util.List;

import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class Sezione {
	private int numeroSezione;
	private boolean randomica;  // true se è una sezione randomica
	private List<Domanda> domande;
	
	public Sezione(int numeroSezione, boolean randomica) {
		super();
		this.numeroSezione = numeroSezione;
		this.randomica = randomica;
		this.domande = new ArrayList<Domanda>();
	}
	
	public boolean creaNuovaDomanda(String domanda) {
		domande.add(new Domanda(domanda));
		return true;
	}

	public int getNumeroSezione() {
		return numeroSezione;
	}

	public void setNumeroSezione(int numeroSezione) {
		this.numeroSezione = numeroSezione;
	}

	public boolean isRandomica() {
		return randomica;
	}

	public void setRandomica(boolean randomica) {
		this.randomica = randomica;
	}

	public List<Domanda> getDomande() {
		return domande;
	}

	public void setDomande(List<Domanda> domande) {
		this.domande = domande;
	}
	
	public void aggiungiNuovaDomanda(String domanda) {
		domande.add(new Domanda(domanda));
	}
	public void visualizzaDomande() {
		if (domande.size() == 0) {
			LinguisticTestsTUI.stampa("Nessuna domanda disponibile in questa sezione.");
		} else {
			for (int i = 0; i < domande.size(); i++) {
				LinguisticTestsTUI.stampa((i + 1) + ") " + domande.get(i).getDomanda());
			}
		}
	}
}
