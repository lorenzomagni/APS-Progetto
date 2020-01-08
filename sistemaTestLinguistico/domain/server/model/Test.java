package sistemaTestLinguistico.domain.server.model;

import java.util.ArrayList;
import java.util.List;

import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class Test {
	private List<Sezione> sezioni;
	private boolean definitivo;
	private String nome;
	private String disturbo;
	
	public Test(String nome, String disturbo) {
		super();
		this.nome = nome;
		this.disturbo = disturbo;
		this.definitivo = false;
		this.sezioni = new ArrayList<Sezione>();
	}
	
	public void creaNuovaSezione() {
		Sezione nuovaSezione = new Sezione(sezioni.size(), false);
		sezioni.add(nuovaSezione);
	}

	public List<Sezione> getSezioni() {
		return sezioni;
	}

	public void setSezioni(List<Sezione> sezioni) {
		this.sezioni = sezioni;
	}

	public boolean isDefinitivo() {
		return definitivo;
	}

	public void setDefinitivo(boolean definitivo) {
		this.definitivo = definitivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDisturbo() {
		return disturbo;
	}

	public void setDisturbo(String disturbo) {
		this.disturbo = disturbo;
	}
	
	public void stampaSezioni() {
		if (sezioni.size() == 0) {
			LinguisticTestsTUI.stampa("Nessuna sezione disponibile in questo test.");
		} else {
			for (int i = 0; i < sezioni.size(); i++) {
				LinguisticTestsTUI.stampa(i+1 + ") Sezione " + (i+1));
			}
		}
	}
	
	
}
