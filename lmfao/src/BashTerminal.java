import java.util.Scanner;
/**
 * This class houses the main entry point of the program. The main method collects
 * user input, its gets parsed in the helper class Command constructor, and the 
 * respective input is passed to one of the `public static DirectoryTree` methods, which 
 * are allowed to manipulate the Directory Tree. (input goes from main->Command ()
 * ->switch->helper method).
 * 
 * TODO fix exceptions
 */
public class BashTerminal {
	/** 
	 * TODO: humor.
	 */
	public static void printPrompt() {System.out.println("[cye6u5vi@bLIUH] $ ");}
	/**
	 * This the entry point to the program. Within it are the variables containing
	 * the directory tree, which gets passed as args to helper methods named after
	 * commands, the Scanner object, which gets closed upon program exit, and the 
	 * switch statement for determining which method of code to run with the command.
	 * 
	 */
	public static void main(String[] args) {
		// not static so it can be passed around between methods.
		DirectoryTree dT = new DirectoryTree();
		
		Scanner s = new Scanner(System.in);
		String next;
		for (boolean done = false;!done;) {
			printPrompt();
			next = s.nextLine();
			next = next.trim();
			Command nextCmd = new Command(next);
			switch (nextCmd.getCmd()) {
				case "pwd": pwd(dT, nextCmd); break;
				case "ls": ls(dT, nextCmd); break;
				case "cd": cd(dT, nextCmd); break;
				case "mkdir": mkdir(dT, nextCmd); break;
				case "touch": touch(dT, nextCmd); break;
				case "exit": done = true; break;
				default: System.out.println("Command not recognized."); 
					continue;
			}
		}
		s.close();
		System.out.println("Program terminating normally.");
		System.exit(0);
	}

	public static DirectoryTree pwd (DirectoryTree dT, Command cmd) {
		System.out.println(dT.presentWorkingDirectory());
		return dT;
	}
	public static DirectoryTree ls (DirectoryTree dT, Command cmd) {
		if (cmd.getArg0() != null) {System.out.println("Sorry that feature isn't implemented yet.");}
		return dT;
	}
	public static DirectoryTree cd (DirectoryTree dT, Command cmd) {
		return dT;
	}
	public static DirectoryTree mkdir (DirectoryTree dT, Command cmd) {
		return dT;
	}
	public static DirectoryTree touch (DirectoryTree dT, Command cmd) {
		return dT;
	}

	/**
	 * This class exists to pass the command and argument between the switch case
	 * and the tree manipulation methods.
	 * 
	 */
	static private class Command {
		private String line;
		private String cmd;
		private String arg0; // no command in the spec has more than one arg

		/**
		 * Constructor parses the line of input.
		 */
		public Command (String line) {
			this.line = line;
			if (line.split(" ").length == 1) {
				this.cmd = line;
			} else {
				cmd = line.split(" ")[0];
				arg0 = line.split(" ")[1];
			}
		}
		public Command () {}

		/**
		 * Functionality from constructor implemented twice in case no-arg
		 * constructor is used.
		 */
		public void parse () {
			if (line.split(" ").length == 1) {
				this.cmd = line;
			} else {
				cmd = line.split(" ")[0];
				arg0 = line.split(" ")[1];
			}
		}

		public String getLine () {return this.line; }
		public String getCmd () {return this.cmd; }
		public String getArg0 () {return this.arg0; }
		public void setLine (String line) {this.line = line; }
	}
}