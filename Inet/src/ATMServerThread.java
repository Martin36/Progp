import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author yo
 */
public class ATMServerThread extends Thread {
	private Socket socket = null;
	private BufferedReader in;
	private PrintWriter out;
	private Account acc;
	private Account[] accounts;
	private Sprak sprak;
	private String commercial;

	public ATMServerThread(Socket socket, Account[] accounts, String commercial) {
		super("ATMServerThread");
		this.socket = socket;
		this.accounts = accounts;
		this.commercial = commercial;
	}

	private String readLine() throws IOException {
		String str = in.readLine();
		return str;
	}

	private boolean validateUser(String pass) {
		return acc.getPass().equals(pass) ;
	}
	
	private boolean checkUser(String cardNr){
		for(int i = 0; i < accounts.length; i++ ){
			if(accounts[i].getCardNr().equals(cardNr)){
				acc = accounts[i];
				if(!acc.loggedIn())
					return true;
			}
		}
		return false;
	}
	public void run() {

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			String inputLine;
			int value;

			sendLine("Welcome to Bank! Please write card number.");
		//	inputLine = in.readLine();
			//Check if the entered card number is in the database
			while(!checkUser(inputLine = in.readLine())){
				sendLine("Account not found in database or already logged in!");
			//	System.exit(1);
			}
			
			sendLine("Enter pass: ");
			inputLine = in.readLine();
			//Check if the user enters the correct password
			while(!validateUser(inputLine)){
				sendLine("Wrong password, try again!");
				//sendLine("Enter pass: ");
				inputLine = in.readLine();

			}
			acc.logIn();		//Here we have enough information to log in to the account
			sprak = new Sprak(acc.getLanguage());		//Sets the preferred language
			
//			sendLine("Welcome to Bank! (1)Balance, (2)Withdrawal, (3)Deposit, (4)Exit");
			sendLine(sprak.options() + ' ' + commercial);			//Sends the options menu on the correct language 
			inputLine = readLine();
			int choice = Integer.parseInt(inputLine);
			while (choice != 5) {
				switch (choice) {
				case 1:
					sendLine(sprak.currentBalance(acc.getBalance()));
					break;
				case 2:
					sendLine(sprak.typeAmount());
					inputLine = readLine();
					value = Integer.parseInt(inputLine);
					sendLine(sprak.insertSecurityCode());
					inputLine = readLine();
					if(acc.withdraw(value, inputLine)){
						sendLine(sprak.currentBalance(acc.getBalance()));		//Withdraws the amount specified if possible then writes the current balance
					}
					else{
						sendLine(sprak.errWith());
					}
					break;
				case 3:
					sendLine(sprak.typeAmount());
					inputLine = readLine();
					value = Integer.parseInt(inputLine);
					if(acc.deposit(value)){
						sendLine(sprak.currentBalance(acc.getBalance()));		//Deposits the specified amount to the account
						
					}
					break;
				case 4:
					sendLine("(1)English, (2)Svenska");
					value = Integer.parseInt(readLine());
					switch(value){
					case 1:
						sprak.changeLanguage(Language.ENGLISH);
						break;
					case 2:
						sprak.changeLanguage(Language.SVENSKA);
						break;
					default:
						break;		
					}
					break;
				default:
					break;
				}
				sendLine(sprak.options());
				inputLine = readLine();
				choice = Integer.parseInt(inputLine);
			}
			acc.logOut();
			sendLine("Good Bye");
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		}
	private void sendLine(String s) {
		char[] c = s.toCharArray();
		
		int i = 0;
		while (i+4 < c.length) {
			out.print(Arrays.copyOfRange(c, i, i+5));
			out.flush();
			i = i+5;
		}
		out.print(Arrays.copyOfRange(c, i, c.length));
		out.print("\n");
		out.flush();
	}
}
