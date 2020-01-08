package sistemaTestLinguistico.domain.server.model;

public class Domanda {
	private String domanda;
	private Slide slide;
	private String risposta;
	
	public Domanda(String domanda) {
		super();
		this.domanda = domanda;
		this.slide = new Slide();
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public Slide getSlide() {
		return slide;
	}

	public void setSlide(Slide slide) {
		this.slide = slide;
	}
	
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	
	public String getRisposta() {
		return risposta;
	}
	
}
