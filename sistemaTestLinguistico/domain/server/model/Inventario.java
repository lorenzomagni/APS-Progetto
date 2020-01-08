package sistemaTestLinguistico.domain.server.model;

import java.util.ArrayList;
import java.util.List;

import sistemaTestLinguistico.ui.view.LinguisticTestsTUI;

public class Inventario {
	private List<User> users;
	private List<Test> testsPilota;
	private List<Test> testsDefinitivi;
	
	
	public Inventario() {
		super();
		users = User.getDefaultUsers();
		testsPilota = new ArrayList<Test>();
		testsDefinitivi = new ArrayList<Test>();
	}
	
	public List<User> getUsers() {
		return users;
	}

	public List<Test> getTestsPilota() {
		return testsPilota;
	}


	public List<Test> getTestsDefinitivi() {
		return testsDefinitivi;
	}

	public Identity login(String username, String password) {
		Identity identity = null;
		boolean presente = false;
		int indiceUtenteTrovato = -1;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
				presente = true;
				indiceUtenteTrovato = i;
			}	
		}
		if (presente) {
			User utenteTrovato = users.get(indiceUtenteTrovato);
			identity = new Identity(utenteTrovato.getUsername(), utenteTrovato.getRuolo());
		}
		return identity;
	}
	
	public void salvaNuovoTestPilota(Test testPilota) {
		testsPilota.add(testPilota);
	}
	
	public void visualizzaTestPilota() {
		if (testsPilota.size() == 0) {
			LinguisticTestsTUI.stampa("Nessun test pilota disponibile. Inserisci un intero qualsiasi per continuare.");
		} else {
			for (int i = 0; i < testsPilota.size(); i++) {
				LinguisticTestsTUI.stampa(i+1 + ") " + testsPilota.get(i).getNome());
			}
		}
	}
	
	public void visualizzaTestDefinitivi() {
		if (testsDefinitivi.size() == 0) {
			LinguisticTestsTUI.stampa("Nessun test definitivo disponibile. Inserisci un intero qualsiasi per continuare.");
		} else {
			for (int i = 0; i < testsDefinitivi.size(); i++) {
				LinguisticTestsTUI.stampa(i+1 + ") " + testsDefinitivi.get(i).getNome());
			}
		}
	}

	
}
