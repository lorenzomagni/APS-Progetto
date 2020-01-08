package sistemaTestLinguistico.domain.server.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username;
	private String password;
	private String ruolo;
	
	private static List<User> defaultUsers = new ArrayList<User>(); //un ArrayList di utenti predefiniti per testare il login
	
	public User(String username, String password, String ruolo) {
		super();
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static List<User> getDefaultUsers() {
		User[] users = {new User("Alberto56", "12345", "esperto"),
		                new User("Luca99", "abcde", "docente"),
		                new User("PaoloRossi", "quaranta42", "medico")};
		for(int i = 0; i < users.length; i++) {
			defaultUsers.add(users[i]);
		}
		
		return defaultUsers;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
