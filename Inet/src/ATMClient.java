import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Snilledata
 */
public class ATMClient {
	private static int connectionPort = 8989;

	Socket ATMSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	String adress = "";
	Scanner scanner;

	public ATMClient(String adress) throws IOException {

		try {
			this.adress = adress;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Missing argument ip-adress");
			System.exit(1);
		}
		try {
			ATMSocket = new Socket(adress, connectionPort);
			out = new PrintWriter(ATMSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(ATMSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Unknown host: " + adress);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't open connection to " + adress);
			System.exit(1);
		}
	  scanner = new Scanner(System.in);

		communication();

	}

	public static void main(String[] args) throws IOException {
		ATMClient client = new ATMClient(args[0]);
	}
	
	private void communication() throws IOException{
		System.out.println("Contacting bank ... ");
		String userInput;

		do{
			System.out.println(in.readLine());
			if(in.ready()){
				System.out.println(in.readLine());				
			}
			System.out.print("> ");
			userInput = scanner.nextLine(); 											
			sendLine(userInput); 
		} while(!userInput.equals("5"));
		
		scanner.close();
		out.close();
		in.close();
		ATMSocket.close();
		
	}
	
	private void sendLine(String s) {
		char[] c = s.toCharArray();

		int i = 0;
		while (i + 4 < c.length) {
			out.print(Arrays.copyOfRange(c, i, i + 5));
			out.flush();
			i = i + 5;
		}
		out.print(Arrays.copyOfRange(c, i, c.length));
		out.print("\n");
		out.flush();
	}
}
