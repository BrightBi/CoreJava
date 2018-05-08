package corejava.chapter3;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InputOutput {

	public static void main(String[] args) throws FileNotFoundException {
		
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		// scannerFromConsole();
		// consoleFromConsole();
		// scannerFromFile();
		writeToFile();
		
	}
	
	public static void scannerFromConsole() {
		String input = null;
		Scanner scan = new Scanner(System.in);
		input = scan.nextLine();
		System.out.println(input);
		scan.close();
	}
	
	public static void scannerFromFile() throws FileNotFoundException {
		String input = null;
		Scanner scan = new Scanner(new File("C:\\test.txt"));
		while (scan.hasNext()) {
			input = scan.nextLine();
			System.out.println(input);
		}
		scan.close();
	}
	
	public static void writeToFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("C:\\test.txt");
		writer.write("write");
		writer.write("next");
		writer.close();
	}
	
	public static void consoleFromConsole() {
		String input = null;
		char [] password = null;
		Console cons = System.console();
		input = cons.readLine("Name:");
		password = cons.readPassword("Password:");
		System.out.println(input);
		System.out.println(password.toString());
	}

}
