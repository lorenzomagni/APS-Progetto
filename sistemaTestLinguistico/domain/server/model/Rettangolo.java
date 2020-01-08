package sistemaTestLinguistico.domain.server.model;

public class Rettangolo {
	private double altezza;
	private double larghezza;
	
	public Rettangolo() {
		super();
		//valori di default
		this.altezza = 50.000;
		this.larghezza = 50.000;
	}

	public double getAltezza() {
		return altezza;
	}

	public void setAltezza(double altezza) {
		this.altezza = altezza;
	}

	public double getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(double larghezza) {
		this.larghezza = larghezza;
	}
	
}
