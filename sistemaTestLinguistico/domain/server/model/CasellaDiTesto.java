package sistemaTestLinguistico.domain.server.model;

public class CasellaDiTesto extends Input {
	private String testoInserito; // la risposta
	
	
	public CasellaDiTesto(String nome) {
		super(nome);
	}
	
	public void setTestoInserito(String testoInserito) {
		this.testoInserito = testoInserito;
	}
}
