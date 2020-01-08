package sistemaTestLinguistico.domain.server.model;

public class ElementoGrafico {
	private double distanzaOrizzontale;
	private double distanzaVerticale;
	private Rettangolo rettangolo;
	private String nome;
	
	public ElementoGrafico(String nome) {
		super();
		this.nome = nome;
		distanzaOrizzontale = 0.000;
		distanzaVerticale = 0.000;
		rettangolo = new Rettangolo();
	}



	public double getDistanzaOrizzontale() {
		return distanzaOrizzontale;
	}

	public void setDistanzaOrizzontale(double distanzaOrizzontale) {
		this.distanzaOrizzontale = distanzaOrizzontale;
	}

	public double getDistanzaVerticale() {
		return distanzaVerticale;
	}

	public void setDistanzaVerticale(double distanzaVerticale) {
		this.distanzaVerticale = distanzaVerticale;
	}
	
	public String getNome() {
		return nome;
	}
}
