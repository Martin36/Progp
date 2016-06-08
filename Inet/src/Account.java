import java.util.LinkedList;

/**
 * Contains all the anonymous account information of a user. Also manages changes in said users balance.
 * @author Martin Funkquist
 * @author David Sjöblom
 * @version 2016-01-18
 */
public class Account {
	private String cardNr;
	private String pass;
	private int balance;
	private Language lang;
	private boolean loggedIn = false;
//	private HashMap<Integer, Boolean> keys;
	private LinkedList<String> keys;
	
	public Account(String cardNr, String pass, int balance, Language lang){
		this.cardNr = cardNr;
		this.pass = pass;
		this.balance = balance;
		this.lang = lang;
		this.keys = new LinkedList<String>();
		for(int i = 0; i < 10; i++){
			for(int j = 1; j < 10; j += 2){
				keys.add("" + i + "" + j);
			}
		}
	}
	public boolean withdraw(int cash, String key){
		if(cash > this.balance || cash <= 0){
			return false;
		}
		if(validateCode(key)){
			this.balance -= cash;
			return true;			
		}
		return false;
	}
	/**
	 * Adds @param cash to the balance
	 * 
	 * @return
	 */
	public boolean deposit(int cash){
		if(cash > 0){
			this.balance += cash;
			return true;
		}
		return false;
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
	public boolean loggedIn(){ return this.loggedIn; }
	/**
	 * Call if account is logged out.
	 */
	public void logOut(){
		this.loggedIn = false;
	}
	public String getPass(){
		return this.pass;
	}
	public String getCardNr(){
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
