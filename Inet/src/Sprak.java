/**
 * Contains lines of different languages
 * @author 
 *
 */

public class Sprak {
	private Language lang;
	
	public Sprak(Language lang){
		this.lang = lang;
	}
	public void changeLanguage(Language lang){
		this.lang = lang;
	}
	public String currentBalance(int balance){
		switch (lang){
			case ENGLISH:
				return "Current balance: " + balance;
			case SVENSKA:
				return "Saldo: " + balance;
			default:
				return "Unknown language.";
		}
	}
	public String cardNr(){
		switch (lang){
		case ENGLISH:
			return "Enter card number: ";
		case SVENSKA:
			return "Skriv in kortnummer: ";
		default:
			return "Unknown language.";
		}
	}
	public String pass(){
		switch (lang){
		case ENGLISH:
			return "Enter password: ";
		case SVENSKA:
			return "Skriv in lösenord: ";
		default:
			return "Unknown language.";
		}
	}
	public String loggedIn(){
		switch (lang){
		case ENGLISH:
			return "You have successfully logged in!";
		case SVENSKA:
			return "Du har loggat in!";
		default:
			return "Unknown language.";
		}
	}
	public String welcome(){
		switch (lang){
		case ENGLISH:
			return "Welcome to this ATM, we wish you a pleasant experience";
		case SVENSKA:
			return "Välkommen till bankomaten ";
		default:
			return "Unknown language.";
		}
	}
	public String options(){
		switch (lang){
		case ENGLISH:
			return "(1)Balance, (2)Withdrawal, (3)Deposit, (4)Change Language, (5)Exit";
		case SVENSKA:
			return "(1)Saldo, (2)Uttag, (3)Insättning, (4)Byt Språk, (5)Avsluta";
		default:
			return "Unknown language.";
		}
	}
	public String typeAmount(){
		switch (lang){
		case ENGLISH:
			return "Enter Amount: ";
		case SVENSKA:
			return "Ange Belopp: ";
		default:
			return "Unknown language.";
		}
	}
	public String insuff(){
		switch (lang){
		case ENGLISH:
			return "Insufficient balance!";
		case SVENSKA:
			return "Utillräckligt saldo!";
		default:
			return "Unknown language.";
		}
	}
	public String negAmount(){
		switch (lang){
		case ENGLISH:
			return "Please enter positive amount.";
		case SVENSKA:
			return "Var god ange positivt värde.";
		default:
			return "Unknown language.";
		}
	}
	public String insertSecurityCode(){
		switch (lang){
		case ENGLISH:
			return "Insert security code: ";
		case SVENSKA:
			return "Ange säkerhetskod: ";
		default:
			return "Unknown language.";
		}
	}	
	public String invalidSecurityCode(){
		switch (lang){
		case ENGLISH:
			return "Invalid security code ";
		case SVENSKA:
			return "Fel säkerhetskod";
		default:
			return "Unknown language.";
		}		
	}
	public String errWith(){
		switch (lang){
		case ENGLISH:
			return "Error withdrawing cash!";
		case SVENSKA:
			return "Uttagsfel!";
		default:
			return "Unknown language.";						
		}
	}
	
}
