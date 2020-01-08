package sistemaTestLinguistico.domain.server.model;

import java.util.ArrayList;
import java.util.List;

public class Tabella extends ElementoGrafico {
	private List<ArrayList<Casella>> caselle;
	
	private int numeroRighe;
	private int numeroColonne;
	
	public Tabella(String nome, int numeroRighe, int numeroColonne) {
		super(nome);
		this.numeroRighe = numeroRighe;
		this.numeroColonne = numeroColonne;
		caselle = new ArrayList<ArrayList<Casella>>();
		
		//aggiungo le righe e le caselle 
		
		for (int i = 0; i < numeroRighe; i++) {
			caselle.add(new ArrayList<Casella>());
			for (int k = 0; k < numeroColonne; k++) {
				caselle.get(i).add(new Casella());
			}
		}	
	}
	
	public void aggiungiElementoGraficoCasella(int riga, int colonna, ElementoGrafico elemento) {
		caselle.get(riga).get(colonna).setElementoGrafico(elemento);
	}

	public List<ArrayList<Casella>> getCaselle() {
		return caselle;
	}

	public void setCaselle(List<ArrayList<Casella>> caselle) {
		this.caselle = caselle;
	}

	public int getNumeroRighe() {
		return numeroRighe;
	}

	public void setNumeroRighe(int numeroRighe) {
		this.numeroRighe = numeroRighe;
	}

	public int getNumeroColonne() {
		return numeroColonne;
	}

	public void setNumeroColonne(int numeroColonne) {
		this.numeroColonne = numeroColonne;
	}
	
}
