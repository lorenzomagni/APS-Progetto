package sistemaTestLinguistico.domain.server.model;

public class Label extends ElementoGrafico {
	private String testo;
	private String font;
	private int dimensioneFont;
	
	public Label(String testo, String font, int dimensioneFont) {
		super(testo);
		this.testo = testo;
		this.font = font;
		this.dimensioneFont = dimensioneFont;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public int getDimensioneFont() {
		return dimensioneFont;
	}

	public void setDimensioneFont(int dimensioneFont) {
		this.dimensioneFont = dimensioneFont;
	}
}
