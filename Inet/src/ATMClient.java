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

		System.out.println("Contacting bank ... ");
		System.out.println(in.readLine());

		Scanner scanner = new Scanner(System.in);
		System.out.print("> ");
		String cardNr = scanner.nextLine(); // Reads the user inputed card
											// number
		sendLine(cardNr); // Sends the card number to the server

		System.out.println(in.readLine()); // Asks for the password
		System.out.print("> ");
		String pass = scanner.nextLine(); // Reads the user inputed password
		sendLine(pass);

		String curr;
		while ((curr = in.readLine()).equals("Wrong password, try again!")) {
			System.out.println(curr);
			System.out.print("> ");
			pass = scanner.nextLine(); // Reads the user inputed password
			sendLine(pass);
		}

		System.out.println(curr); // Prints the menu
		System.out.print("> ");
		int menuOption = scanner.nextInt();
		int userInput;
		sendLine(menuOption + "");

		while (menuOption != 5) {

			if (menuOption == 1) { // Balance
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.print("> ");
			menuOption = scanner.nextInt();
			sendLine(menuOption + "");
			}

			else if (menuOption == 2) { // Withdrawal
			System.out.println(in.readLine());
			userInput = scanner.nextInt();
			sendLine(userInput + "");
			System.out.println(in.readLine());
			String key = scanner.next(); // Reads input valid code line
			sendLine(key); // The key input made by the user
			String str;
			do {
				str = in.readLine();
				System.out.println(str);
			} while (!str.startsWith("(1)"));
			System.out.print("> ");
			menuOption = scanner.nextInt();
			sendLine(menuOption + "");
			}

			else if (menuOption == 3) {
			System.out.println(in.readLine());
			userInput = scanner.nextInt();
			sendLine(userInput + "");
			String str;
			do {
				str = in.readLine();
				System.out.println(str);
			} while (!str.startsWith("(1)"));
			System.out.print("> ");
			menuOption = scanner.nextInt();
			sendLine(menuOption + "");

			}

			else if (menuOption == 4) { // Arrives here if language option is
										// selected in menu
			System.out.println(in.readLine());
			System.out.print("> ");
			userInput = scanner.nextInt();
			sendLine(userInput + "");
			System.out.println(in.readLine());
			System.out.print("> ");
			userInput = scanner.nextInt();
			menuOption = userInput;
			sendLine(userInput + "");
			}

			else if (menuOption == 5) {
			break;
			}

			else {
			System.out.println(in.readLine());
			System.out.print("> ");
			menuOption = scanner.nextInt();
			sendLine(menuOption + "");
			}
		}
		scanner.close();
		out.close();
		in.close();
		ATMSocket.close();

	}

	public static void main(String[] args) throws IOException {
		ATMClient client = new ATMClient(args[0]);
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
