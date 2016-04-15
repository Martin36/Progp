import java.io.*;   
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

/**
   @author Snilledata
*/
public class ATMClient {
	private static int connectionPort = 8989;
	  
	  public static void main(String[] args) throws IOException {
        
      Socket ATMSocket = null;
      PrintWriter out = null;
      BufferedReader in = null;
      String adress = "";

	    try {
        adress = args[0];
	    } catch (ArrayIndexOutOfBoundsException e) {
        System.err.println("Missing argument ip-adress");
        System.exit(1);
	    }
	    try {
        ATMSocket = new Socket(adress, connectionPort); 
        out = new PrintWriter(ATMSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader
                                (ATMSocket.getInputStream()));
	    } catch(UnknownHostException e) {
        System.err.println("Unknown host: " +adress);
        System.exit(1);
	    } catch (IOException e) {
        System.err.println("Couldn't open connection to " + adress);
        System.exit(1);
	    }

	    System.out.println("Contacting bank ... "); 
	    System.out.println(in.readLine()); 

	    Scanner scanner = new Scanner(System.in);
	    System.out.print("> ");
	    String cardNr = scanner.nextLine();		//Reads the user inputed card number
	    out.println(cardNr);			//Sends the card number to the server
	    
	    System.out.println(in.readLine());		//Asks for the password
	    System.out.print("> ");
	    String pass = scanner.nextLine(); 		//Reads the user inputed password
	    out.println(pass);
	    
	    System.out.println(in.readLine());
	    
	    System.out.print("> ");
	    int menuOption = scanner.nextInt();
	    int userInput;
	    out.println(menuOption);
	    
	    while(menuOption < 5) {
	      if(menuOption == 1) {
          System.out.println(in.readLine()); 
          System.out.println(in.readLine());
          System.out.print("> ");
          menuOption = scanner.nextInt();
          out.println(menuOption);           
        } else if (menuOption > 4) {	
        		break;
        }	else if(menuOption == 4){			//Arrives here if language option is selected in menu
	        	System.out.println(in.readLine());
	        	System.out.print("> ");
	        	userInput = scanner.nextInt();
	        	out.println(userInput);
	        	System.out.println(in.readLine());
	        	System.out.print("> ");
	        	userInput = scanner.nextInt();
	        	out.println(userInput);
        } else if (menuOption == 2){
	          System.out.println(in.readLine()); 
	          userInput = scanner.nextInt();
	          out.println(userInput);
	          System.out.println(in.readLine()); 
	          String key = scanner.next();		//Reads input valid code line
	          out.println(key);					//The key input made by the user
	          String str;
	          do {
	            str = in.readLine();
	            System.out.println(str);
	          } while (! str.startsWith("(1)"));
	          System.out.print("> ");
	          menuOption = scanner.nextInt();
	          out.println(menuOption);           
          } else{
	          System.out.println(in.readLine()); 
	          userInput = scanner.nextInt();
	          out.println(userInput);
	          String str;
	          do {
	            str = in.readLine();
	            System.out.println(str);
	          } while (! str.startsWith("(1)"));
	          System.out.print("> ");
	          menuOption = scanner.nextInt();
	          out.println(menuOption);           
          	
          }
        }		
	    scanner.close();
      out.close();
      in.close();
      ATMSocket.close();
    }

}   