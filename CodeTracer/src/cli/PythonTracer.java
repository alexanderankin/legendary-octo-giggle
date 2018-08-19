package cli;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import api.CodeParser;
import api.Complexity;

public class PythonTracer {
	public static final boolean DEBUG = false;
	public static final int SPACE_COUNT = 4;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String input;
		boolean True = true;
		while (True) {
			System.out.print("Please enter a file name (or 'quit' to quit): ");
			input = s.next();

			switch (input.toLowerCase()) {
			case "quit":
			case "q":
				System.out.println("Program terminating successfully...");
				System.exit(0);
				break;

			default:
				input = "psp/" + input;
				if (!(new File(input).exists())) {
					System.out.println("No File Found");
					continue;
				}

				System.out.println(traceFile(input).toString());
				break;
			}
		}
		s.close();
		System.exit(0);

	}

	private static Complexity traceFile(String fileName) {
		ArrayList<String> input = readFile(fileName);
		String[] program = new String[input.size()];
		program = input.toArray(program);

		CodeParser cp = new CodeParser(program);
		
		return cp.traceProgram();
	}

	private static ArrayList<String> readFile(String fileName) {
		ArrayList<String> program = new ArrayList<String>();
		try {
			Scanner s = new Scanner(new File(fileName));
			while (s.hasNext()) {
				String string = (String) s.nextLine();
				String oString = string;
				string = string.trim();
				if (string.startsWith("#") || string.equals(""))
					continue;
				program.add(oString);
			}
			s.close();
		} catch (Exception e) {
			// Never happens.
		}
		return program;
	}
}
