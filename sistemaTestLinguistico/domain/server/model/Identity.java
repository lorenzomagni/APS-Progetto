package sistemaTestLinguistico.domain.server.model;

// resituita dall'operazione sistemaController.login()
public class Identity {
	private String username;
	private String ruolo;

	public Identity(String username, String ruolo) {
		super();
		this.username = username;
		this.ruolo = ruolo;
	}
	
	public String getUsername() {
		return username;
	}

	public String getRuolo() {
		return ruolo;
	}
	
}
