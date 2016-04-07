import java.net.*;
import java.io.*;

/**
   @author Viebrapadata
*/
public class ATMServer {

	private static int connectionPort = 8989;
	private static Sprak defaultSprak = new Sprak(Language.ENGLISH);
	private static String defaultWelcomeMessage = defaultSprak.welcome();
  
  public static void main(String[] args) throws IOException {
  	Account[] accounts = {
  			new Account(1111, 1234, 10000, Language.ENGLISH),
  			new Account(2222, 4321, 10000000, Language.SVENSKA),
  			new Account(3333, 2345, 9857673, Language.ENGLISH)
  	};
  	
     
      ServerSocket serverSocket = null;
     
      boolean listening = true;
      
      try {
        serverSocket = new ServerSocket(connectionPort); 
      } catch (IOException e) {
      		System.err.println("Could not listen on port: " + connectionPort);
          System.exit(1);
      }

      System.out.println("Bank started listening on port: " + connectionPort);
      while (listening)
        new ATMServerThread(serverSocket.accept(), accounts).start();

      serverSocket.close();
  }
}
