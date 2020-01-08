package sistemaTestLinguistico.domain.server.model;

import java.util.ArrayList;
import java.util.List;

import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class Slide {
	private List<ElementoGrafico> elementiGrafici;
	
	public Slide() {
		super();
		this.elementiGrafici = new ArrayList<ElementoGrafico>();
	}
	
	//rispondo alla casella di testo
	public void aggiungiRisposta(String testo) {
		int indiceCasellaDiTesto = -1;
		
		//Suppongo che ci sia una sola casella di testo
		for (int i = 0; i < elementiGrafici.size(); i++) {
			if (elementiGrafici.get(i) instanceof CasellaDiTesto) {
				indiceCasellaDiTesto = i;
				break;
			}
		}
		if (indiceCasellaDiTesto > -1)  {
			((CasellaDiTesto) elementiGrafici.get(indiceCasellaDiTesto)).setTestoInserito(testo);
			LinguisticTestsTUI.stampa("Risposta aggiunta con successo!");
		} else 
			LinguisticTestsTUI.stampa("Errore: impossibile aggiungere risposta alla domanda perchè manca la casella di testo");
		
	}

	public List<ElementoGrafico> getElementiGrafici() {
		return elementiGrafici;
	}

	public void setElementiGrafici(List<ElementoGrafico> elementiGrafici) {
		this.elementiGrafici = elementiGrafici;
	}
	
	public void visualizzaElementiGrafici() {
		for (int i = 0; i < elementiGrafici.size(); i++) {
			LinguisticTestsTUI.stampa(i+1 + ") " + elementiGrafici.get(i).getNome());
		}
	}
	
	public boolean contieneCasellaTesto() {
		boolean contiene = false;
		for (int i = 0; i < elementiGrafici.size(); i++) {
			if (elementiGrafici.get(i) instanceof CasellaDiTesto) {
				contiene = true;
				break;
			}
		}
		return contiene;
	}
	
}
