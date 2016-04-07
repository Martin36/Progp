import java.util.LinkedList;

/**
 * Contains all the anonymous account information of a user. Also manages changes in said users balance.
 * @author Martin Funkquist
 * @author David Sjöblom
 * @version 2016-01-18
 */
public class Account {
	private int cardNr;
	private int pass;
	private int balance;
	private Language lang;
	private boolean loggedIn = false;
//	private HashMap<Integer, Boolean> keys;
	private LinkedList<String> keys;
	
	public Account(int cardNr, int pass, int balance, Language lang){
		this.cardNr = cardNr;
		this.pass = pass;
		this.balance = balance;
		this.lang = lang;
		keys = new LinkedList<String>();
		for(int i = 0; i < 10; i++){
			for(int j = 1; j < 10; j += 2){
				keys.add("" + i + "" + j);
			}
		}
	}
	public String withdraw(int cash, String key){
		if(balance < cash){
			return "Insufficient balance!";
		}
		if(validateCode(key)){
			this.balance -= cash;
			return "Current balance: " + this.balance;			
		}
		else{
			return "Invalid security code!";
		}
	}
	/**
	 * Adds @param cash to the balance
	 * 
	 * @return
	 */
	public String deposit(int cash){
		if(cash < 0)
			return "Please give positive amount.";
		this.balance += cash;
		return "Current balance: " + this.balance;
	}
	/**
	 * Sets the language to @param newLang.
	 */
	public void setLanguage(Language newLang){
		this.lang = newLang;
	}
	/**
	 * Call if account is logged in.
	 */
	public void logIn(){
		this.loggedIn = true;
	}
	/**
	 * Call if account is logged out.
	 */
	public void logOut(){
		this.loggedIn = false;
	}
	public int getPass(){
		return this.pass;
	}
	public int getCardNr(){
		return this.cardNr;
	}
	public Language getLanguage(){
		return this.lang;
	}
	public int getBalance(){
		return this.balance;
	}
	private boolean validateCode(String key){
		if(keys.contains(key)){
			keys.remove(key);
			return true;
		}
		return false;
	}
	/*
	public String getBalanceString(){
		return "Current balance is: " + this.balance;
	}
	*/
}
