package sistemaTestLinguistico.domain.controller;

import java.util.List;

import sistemaTestLinguistico.domain.server.model.*;
import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class ModificaTestPilotaController {
	private Inventario inventario;
	private boolean stopModificaTest = false;
	private Test testInModifica;
	
	public ModificaTestPilotaController(Inventario inventario) {
		super();
		this.inventario = inventario;
	}
	
	public void modificaTestPilota(Test test) {
		stopModificaTest = false; //potrebbe essere stato modificato a true in precedenza per uscire dal while
		testInModifica = test;  //in modo da poter accedervi da altri metodi
		
		while (!stopModificaTest) {
			//Opzioni di modifica test disponibili
			LinguisticTestsTUI.stampaNewLine();
			LinguisticTestsTUI.stampa("Quale operazione di modifica vuoi fare nel test " + testInModifica.getNome() + "?");
			LinguisticTestsTUI.stampa("1) Aggiungere una nuova sezione");
			LinguisticTestsTUI.stampa("2) Modificare una sezione");
			LinguisticTestsTUI.stampa("3) Eliminare una sezione");
			LinguisticTestsTUI.stampa("4) Uscire dalla modalità di modifica test");
			
			int comando = LinguisticTestsTUI.leggiIntero();
			eseguiComandoModifica(comando, test);
		}
	}

	
	private void eseguiComandoModifica(int comando, Test test) {
		switch(comando) {
			case 1: //aggiunge una nuova sezione
				test.creaNuovaSezione();
				LinguisticTestsTUI.stampa("Nuova sezione aggiunta con successo!");
				break;
			case 2: //modifica una sezione
				LinguisticTestsTUI.stampa("Quale sezione desideri modificare? (Digita un intero)");
				List<Sezione> sezioni = test.getSezioni();
				if (sezioni.size() == 0) 
					LinguisticTestsTUI.stampa("Nessuna sezione presente in questo test.");
				else {
					for (int i = 0; i < sezioni.size(); i++) {
						LinguisticTestsTUI.stampa("Sezione " + (i+1));
					}
					int numeroSezione = LinguisticTestsTUI.leggiIntero();
					
					if (numeroSezione <= sezioni.size() && numeroSezione > 0) {
						modificaSezione(sezioni.get(numeroSezione-1));
					} else 
						LinguisticTestsTUI.stampa("Errore: Sezione non valida");
				}
				break;
				
			case 3: //elimina una sezione
				break;
				
			case 4: //esco dalla modifica del test pilota
				stopModificaTest = true;
				break;
			default:
				
		}
		
	}

	
	private void modificaSezione(Sezione sezione) {
		boolean stopModificaSezione = false;
		
		while (!stopModificaSezione) {
			LinguisticTestsTUI.stampaNewLine();
			LinguisticTestsTUI.stampa("Come vuoi modificare la sezione?");
			LinguisticTestsTUI.stampa("1) Cambia tipo di sezione (randomica o no)");
			LinguisticTestsTUI.stampa("2) Aggiungi nuova domanda");
			LinguisticTestsTUI.stampa("3) Sposta una domanda (anche in un altro test)");
			LinguisticTestsTUI.stampa("4) Modifica una domanda (e la corrispondente slide)");
			LinguisticTestsTUI.stampa("5) Esci");
			
			
			int comando = LinguisticTestsTUI.leggiIntero();
			if (comando == 5) 
				stopModificaSezione = true;
			else
				eseguiModificaSezione(comando, sezione);
		}	
	}
	
	private void eseguiModificaSezione(int comando, Sezione sezione) {
		switch(comando) {
			case 1: // cambio sezione randomica in non o viceversa
				if (sezione.isRandomica()) {
					LinguisticTestsTUI.stampa("La sezione randomica è stata cambiata in non randomica.");
					sezione.setRandomica(false);
				} else {
					LinguisticTestsTUI.stampa("La sezione non randomica è stata cambiata in randomica.");
					sezione.setRandomica(true);
				}
				break;
			case 2: //aggiungo una nuova domanda
				aggiungiNuovaDomanda(sezione);
				break;
			case 3: //sposto una domanda in un'altra sezione od in un altro test
				spostaDomanda(sezione);
				break;
			case 4: //modifico una domanda/slide e i relativi elementi grafici che contiene
				modificaSlideDomanda(sezione);
				break;
			
			default:
				LinguisticTestsTUI.stampa("Opzione inserita non valida. Riprova");
		}
	}
	
	private void spostaDomanda(Sezione sezione) {
		LinguisticTestsTUI.stampaNewLine();
		LinguisticTestsTUI.stampa("Quale domanda vuoi spostare?");
		sezione.visualizzaDomande();
		int numeroDomanda = LinguisticTestsTUI.leggiIntero();
		if (numeroDomanda <= sezione.getDomande().size() && numeroDomanda > 0) {
			LinguisticTestsTUI.stampaNewLine();
			LinguisticTestsTUI.stampa("Dove vuoi spostare questa domanda?");
			LinguisticTestsTUI.stampa("1) In un altra sezione dello stesso test");
			LinguisticTestsTUI.stampa("2) In un altra sezione di un altro test");
			int numeroOpzione = LinguisticTestsTUI.leggiIntero();
			eseguiSpostamentoDomanda(numeroOpzione, sezione, sezione.getDomande().get(numeroDomanda-1));
		} else
			LinguisticTestsTUI.stampa("Errore: domanda selezionata non valida.");
			
	}
	
	private void eseguiSpostamentoDomanda(int numeroOpzione, Sezione sezione, Domanda domanda) {
		switch (numeroOpzione) {
			case 1: //sposto la domanda in un'altra sezione dello stesso test
				LinguisticTestsTUI.stampa("In quale sezione del test \"" + testInModifica.getNome() + "\" vuoi spostare la domanda?");
				testInModifica.stampaSezioni();
				int numeroSezione = LinguisticTestsTUI.leggiIntero();
				if (numeroSezione <= testInModifica.getSezioni().size() && numeroSezione > 0) {
					//Se la sezione selezionata è la stessa non faccio niente
					if (testInModifica.getSezioni().get(numeroSezione-1) == sezione) {
						LinguisticTestsTUI.stampa("La domanda è rimasta nella stessa sezione.");
					} else {
						//Altrimenti sposto
						sezione.getDomande().remove(domanda);
						testInModifica.getSezioni().get(numeroSezione-1).getDomande().add(domanda);
						LinguisticTestsTUI.stampa("Domanda spostata con successo!");
						LinguisticTestsTUI.stampaNewLine();
				
					}
				} else
					LinguisticTestsTUI.stampa("Errore: sezione selezionata non valida.");
				LinguisticTestsTUI.stampa("");
				break;
			case 2: //sposto la domanda in un'altra sezione di un'altro test
				LinguisticTestsTUI.stampa("In quale altro test vuoi spostare la domanda?");
				inventario.visualizzaTestPilota();
				int numeroTest = LinguisticTestsTUI.leggiIntero();
				Test nuovoTest = inventario.getTestsPilota().get(numeroTest-1);
				LinguisticTestsTUI.stampa("In quale sezione vuoi spostare la domanda?");
				nuovoTest.stampaSezioni();
				int nuovoNumeroSezione = LinguisticTestsTUI.leggiIntero();
				((Sezione) nuovoTest.getSezioni().get(nuovoNumeroSezione-1)).getDomande().add(domanda);
				sezione.getDomande().remove(domanda);
				break;
			default:
		}
		
	}



	private void aggiungiNuovaDomanda(Sezione sezione) {
		LinguisticTestsTUI.stampa("Inserisci la nuova domanda:");
		String domanda = LinguisticTestsTUI.leggiStringaConSpazi();
		sezione.aggiungiNuovaDomanda(domanda);
	}
	
	private void modificaSlideDomanda(Sezione sezione) {
		LinguisticTestsTUI.stampa("Quale domanda vuoi modificare?");
		sezione.visualizzaDomande();
		int numeroDomanda = LinguisticTestsTUI.leggiIntero();
		
		if (numeroDomanda <= sezione.getDomande().size() && numeroDomanda > 0) {
			Slide slideDaModificare = sezione.getDomande().get(numeroDomanda-1).getSlide(); 
			LinguisticTestsTUI.stampaNewLine();
			LinguisticTestsTUI.stampa("Come vuoi modificare la slide della domanda?");
			LinguisticTestsTUI.stampa("1) Aggiungi un nuovo elemento grafico");
			LinguisticTestsTUI.stampa("2) Modifica un elemento grafico");
			LinguisticTestsTUI.stampa("3) Elimina un elemento grafico");
			int comando = LinguisticTestsTUI.leggiIntero();
			
			switch(comando) {
			case 1: //Aggiungo un nuovo elemento grafico
				ElementoGrafico elementoGrafico = creaNuovoElementoGrafico();
				if (elementoGrafico != null) {
					slideDaModificare.getElementiGrafici().add(elementoGrafico);
				}
				break;
			case 2:
				modificaElementoGrafico(slideDaModificare);
				break;
			case 3:
				eliminaElementoGrafico(slideDaModificare);
				break;
			default:
				LinguisticTestsTUI.stampa("Errore: Operazione non valida.");
			}
		}
	}

	private void eliminaElementoGrafico(Slide slideDaModificare) {
		LinguisticTestsTUI.stampa("Quale elemento grafico desideri eliminare?");
		slideDaModificare.visualizzaElementiGrafici();
		int indiceElemento = LinguisticTestsTUI.leggiIntero();
		List<ElementoGrafico> elementiGrafici = slideDaModificare.getElementiGrafici();
		if (indiceElemento <= elementiGrafici.size() && indiceElemento > 0) {
			elementiGrafici.remove(indiceElemento-1);
		} else
			LinguisticTestsTUI.stampa("Elemento grafico selezionato non valido.");
	}

	private void modificaElementoGrafico(Slide slideDaModificare) {
		//Qui implemento la possibilità di aggiungere un elemento grafico in una casella della tabella,
		// di spostare un elemento grafico in una slide e di cambiare dimensione font di una label
		LinguisticTestsTUI.stampa("Quale elemento grafico desideri modificare?");
		slideDaModificare.visualizzaElementiGrafici();
		int indiceElemento = LinguisticTestsTUI.leggiIntero();
			
		List<ElementoGrafico> elementiGrafici = slideDaModificare.getElementiGrafici();
		if (indiceElemento <= elementiGrafici.size() && indiceElemento > 0) {
			ElementoGrafico elementoGrafico = elementiGrafici.get(indiceElemento-1);
			
			// stampo le possibili operazioni di editing
			LinguisticTestsTUI.stampaNewLine();
			LinguisticTestsTUI.stampa("Cosa desideri fare?");
			LinguisticTestsTUI.stampa("1) Sposta elemento grafico");
			if (elementoGrafico instanceof Label) {
				LinguisticTestsTUI.stampa("2) Cambia dimensione font");
			} else if (elementoGrafico instanceof Tabella) {
				LinguisticTestsTUI.stampa("3) Inserisci un elemento grafico in una casella");
			}
				
			int opzione = LinguisticTestsTUI.leggiIntero();
				
				// eseguo l'operazione
			switch (opzione) {
				case 1:
					spostaElementoGrafico(elementoGrafico);
					break;
				case 2:
					if (elementoGrafico instanceof Label) {
						cambiaDimensioneFontLabel(elementoGrafico);
					} else
						LinguisticTestsTUI.stampa("Errore: operazione non valida");
					break;
				case 3:
					if (elementoGrafico instanceof Tabella) {
						aggiungiElementoCasellaTabella(elementoGrafico);
					} else
						LinguisticTestsTUI.stampa("Errore: operazione non valida");
					break;
				default:
					LinguisticTestsTUI.stampa("Errore: operazione non valida");
			}
			
			
		} else
			LinguisticTestsTUI.stampa("Elemento grafico selezionato non valido.");
	}

	
	private void aggiungiElementoCasellaTabella(ElementoGrafico elementoGrafico) {
		LinguisticTestsTUI.stampa("Inserisci la riga della casella:");		
		int riga = LinguisticTestsTUI.leggiIntero();
		LinguisticTestsTUI.stampa("Inserisci la colonna della casella:");		
		int colonna = LinguisticTestsTUI.leggiIntero();
		Tabella tabella = (Tabella) elementoGrafico;
		Casella casella = tabella.getCaselle().get(riga).get(colonna);
		ElementoGrafico elementoDaAggiungere = creaNuovoElementoGrafico();
		casella.setElementoGrafico(elementoDaAggiungere);
	}

	private void cambiaDimensioneFontLabel(ElementoGrafico elementoGrafico) {
		LinguisticTestsTUI.stampa("Inserisci la nuova dimensione del font della label:");
		int dimensioneFont = LinguisticTestsTUI.leggiIntero();
		((Label) elementoGrafico).setDimensioneFont(dimensioneFont);
		LinguisticTestsTUI.stampa("Dimensione font della label aggiornata con successo!");
	}

	private void spostaElementoGrafico(ElementoGrafico elementoGrafico) {
		LinguisticTestsTUI.stampa("Inserisci la distanza orizzontale in double con 3 cifre decimali:");
		double distanzaOrizzontale = LinguisticTestsTUI.leggiDouble();
		LinguisticTestsTUI.stampa("Inserisci la distanza verticale in double con 3 cifre decimali:");
		double distanzaVerticale = LinguisticTestsTUI.leggiDouble();
		elementoGrafico.setDistanzaOrizzontale(distanzaOrizzontale);
		elementoGrafico.setDistanzaVerticale(distanzaVerticale);
		LinguisticTestsTUI.stampa("Elemento grafico spostato nella slide con successo!");
	}

	private ElementoGrafico creaNuovoElementoGrafico() {
		LinguisticTestsTUI.stampaNewLine();
		LinguisticTestsTUI.stampa("Quale elemento grafico vuoi aggiungere?");
		LinguisticTestsTUI.stampa("1) Label");
		LinguisticTestsTUI.stampa("2) Tabella");
		LinguisticTestsTUI.stampa("3) Casella di testo (input)");
		LinguisticTestsTUI.stampa("4) Riquadro immagine");
		int elemGrafico = LinguisticTestsTUI.leggiIntero();
		
		ElementoGrafico nuovoElementoGrafico = null;
		switch(elemGrafico) {
			case 1: //creo una label
				LinguisticTestsTUI.stampa("Inserisci il testo della label:");
				String testo = LinguisticTestsTUI.leggiStringaConSpazi();
				LinguisticTestsTUI.stampa("Inserisci la dimensione del font:");
				int dimensioneFont = LinguisticTestsTUI.leggiIntero();
				LinguisticTestsTUI.stampa("Inserisci il nome del font:");
				String nomeFont = LinguisticTestsTUI.leggiStringaConSpazi();
				nuovoElementoGrafico = new Label(testo, nomeFont, dimensioneFont);
				LinguisticTestsTUI.stampa("Nuova label aggiunta con successo!");
				break;
			
			case 2: // creo una tabella
				LinguisticTestsTUI.stampa("Inserisci il nome della tabella:");
				String nome = LinguisticTestsTUI.leggiStringaConSpazi();
				LinguisticTestsTUI.stampa("Inserisci il numero di righe:");
				int numeroRighe = LinguisticTestsTUI.leggiIntero();
				LinguisticTestsTUI.stampa("Inserisci il numero di colonne:");
				int numeroColonne = LinguisticTestsTUI.leggiIntero();
				nuovoElementoGrafico = new Tabella(nome, numeroRighe, numeroColonne);
				LinguisticTestsTUI.stampa("Nuova tabella aggiunta con successo!");
				break;
				
			case 3: // creo una casella di testo
				LinguisticTestsTUI.stampa("Inserisci il nome della casella di teso:");
				String nomeCasellaTesto = LinguisticTestsTUI.leggiStringaConSpazi();
				nuovoElementoGrafico = new CasellaDiTesto(nomeCasellaTesto);
				LinguisticTestsTUI.stampa("Nuova casella di testo aggiunta con successo!");
				break;
			case 4: // creo un riquadro immagine
				LinguisticTestsTUI.stampa("Inserisci il nome del riquadro immagine:");
				String nomeRiquadro = LinguisticTestsTUI.leggiStringaConSpazi();
				nuovoElementoGrafico = new RiquadroImmagine(nomeRiquadro);
				LinguisticTestsTUI.stampa("Nuovo riquadro immagine aggiunto con successo!");
				break;
			default:
				LinguisticTestsTUI.stampa("Elemento grafico selezionato non valido.");
		}
		
		return nuovoElementoGrafico;
	}
	
}
