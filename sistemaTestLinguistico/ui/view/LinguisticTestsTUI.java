package sistemaTestLinguistico.ui.view;

import java.util.List;
import java.util.Scanner;

import sistemaTestLinguistico.domain.controller.SystemController;
import sistemaTestLinguistico.domain.server.model.Identity;
import sistemaTestLinguistico.domain.server.model.User;

public class LinguisticTestsTUI {
	private static SystemController systemController;
	
	public LinguisticTestsTUI() {
		super();
		this.systemController = new SystemController();
	}
	
	
	//Il controllo del programma, l'esecuzione
	public void start() {
		boolean loggedIn = false;
		Identity identity;
		boolean done = false;
		boolean termina = false;
		
		while (!termina) {
			done = false;
			loggedIn = false;
			do {
				System.out.println("Salve, inserisca i suoi dati per effettuare il login.");
				System.out.println("-------------------------------");
				System.out.println("Utenti di default nel sistema da usare come prova:");
				List<User> accounts = systemController.getInventario().getUsers();
				for (int i = 0; i < accounts.size(); i++) {
					System.out.println("Username: " + accounts.get(i).getUsername() + "   Password: " + accounts.get(i).getPassword());
				}
				System.out.println("--------------------------------");
				System.out.println();
				System.out.println("Username: ");
				String username = leggiStringa();
				System.out.println("Password:");
				String password = leggiStringa();
				System.out.println("Effettuo login, attendere...");
				System.out.println();
				// delego il login al sistemaController
				identity = systemController.login(username, password);
				
				if (identity != null) {
					loggedIn = true;
				} else
					System.out.println("Username o password errati, riprova.");
			} while(!loggedIn);
		
			System.out.println("Benvenuto " + identity.getUsername() + "!");
		
			while (!done) {
				int codiceOperazione;
				
				System.out.println();
				System.out.println("--------------------------");
				System.out.println("Cosa desideri fare ora?");
				System.out.println("1) Creare nuovo test pilota");
				System.out.println("2) Creare test definitivo");
				System.out.println("3) Modifica un test pilota");
				System.out.println("4) Somministra test linguistico");
				System.out.println("5) Uscire dal programma");
				System.out.println("6) Logout");
				
				codiceOperazione = leggiIntero();
				if (codiceOperazione == 5)  {
					done = true;
					termina = true;
				} else if(codiceOperazione == 6) {
					done = true;
				} else
					systemController.eseguiOperazioneIniziale(identity, codiceOperazione);
			}
		}
	
	}
	
	//metodi statici
	public static void stampa(String messaggio) {
		System.out.println(messaggio);
	}
	
	public static int leggiIntero() {
		Scanner tastiera = new Scanner(System.in);
		return tastiera.nextInt();
	}
	
	public static String leggiStringa() {
		Scanner tastiera = new Scanner(System.in);
		return tastiera.next();
	}
	
	public static String leggiStringaConSpazi() {
		Scanner tastiera = new Scanner(System.in);
		return tastiera.nextLine();
	}
	
	public static void stampaNewLine() {
		System.out.println();
	}
	
	public static double leggiDouble() {
		Scanner tastiera = new Scanner(System.in);
		
		try {
			return tastiera.nextDouble();
		} catch (Exception e) {
			System.out.println("Errore: non hai inserito un double corretto.");
		}
		return 0;
	}
	
	
}