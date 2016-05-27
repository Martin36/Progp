import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/*
 * @author Martin & David
 * @version 2016-05-27
 */
public class ATMServer {

	private static int connectionPort = 8989;
	private static Sprak defaultSprak = new Sprak(Language.ENGLISH);
	private static String commercial = "";

	public static void main(String[] args) throws IOException {
		Account[] accounts = { new Account("1111", "1234", 10000, Language.ENGLISH),
													 new Account("1234567891234", "4321", 10000000, Language.SVENSKA), 
													 new Account("3333", "2345", 9857673, Language.ENGLISH) };
	
		ServerSocket serverSocket = null;
//		Scanner scanner = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean listening = true;
	
		try {
			serverSocket = new ServerSocket(connectionPort);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + connectionPort);
			System.exit(1);
		}
	
		System.out.println("Bank started listening on port: " + connectionPort);
		serverSocket.setSoTimeout(5000);
		Socket socket;
		while (listening) {
			try {
//				System.out.println("Hej!");
				socket = serverSocket.accept();
				if (socket.isConnected()) {
					System.out.println("Socket Connected!!!!");
					new ATMServerThread(socket, accounts, commercial).start();
				}
			} catch (SocketTimeoutException e) {
			}
			if(in.ready()){
				String input = in.readLine();
				if(input.equals("exit")){
					listening = false;
					System.out.println("Server shutting down!");
				} 
				else if(input.length() <= 80){
					commercial = input;
					System.out.println("Successfully updated commercial message!");
				}
				else{
					System.out.println("Unable to update commercial field, string exceeded current maximum lenght!");
				}				
			}
		}
	
		serverSocket.close();
		System.exit(0);
		}
}
