package api;

import java.util.ArrayList;
import java.util.Arrays;

import cli.PythonTracer;

public class CodeParser {
	private String[] program;
	private BlockStack bS = new BlockStack();
	private ArrayList<Integer> counter = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
	private int counterPointer = 0;

	public CodeParser(String[] program) {
		this.program = program;
	}

	public CodeParser() {
	}

	public Complexity traceProgram() {
		return traceProgram(this.program);
	}

	public Complexity traceProgram(String[] program) {
		for (int i = 0; i < program.length; i++) {

			String line = program[i];

			if (prevGtCurrent(program, i)) {
				POP(program, i);
			}

			if (hasKeyword(line)) {
				// gather all the details and perform push after switch
				// statement
				Complexity newComplexity = new Complexity(0, 0);
				int newType = 0;
				String newName = "";

				// adjust counter and assign newName
				counter.set(counterPointer, new Integer(counter.get(counterPointer) + 1));
				counterPointer++;
				newName = newNameFromCounter();
				switch (whichKeyword(line)) {
				case "def":
					newType = 0;
					// make stack
					newName = "1";
					break;
				case "for":
					newType = 1;
					// potentially re-factor into -> to find N: and log_N:
					newComplexity = (line.contains("log")) ? new Complexity(0, 1) : new Complexity(1, 0);
					break;
				case "while":
					newType = 2;
					break;
				case "if":
					newType = 3;
					break;
				case "elif":
					newType = 4;
					break;
				case "else":
					newType = 5;
					break;
				default:
					// never happens
					// System.out.println("default switch case wtf");
					break;
				}

				bS.push(new CodeBlock(newComplexity, new Complexity(0, 0)).setType(newType).setName(newName));

				{
					System.out.println(String.format(
							"    Entering block %s, '%s\n\tBLOCK %-10sblock complexity = %-10shighest sub-complexity = %s",
							newName, CodeBlock.BLOCK_TYPES[newType] + "': ", newName,
							bS.peek().getBlockComplexity().toString(), new Complexity().toString()));
				}
				continue;
			} else {
				if (bS.size() != 0 && bS.peek().getType() == 2) {
					if (line.contains("/=")) {
						bS.peek().setBlockComplexity(new Complexity(0, 1));
						{
							System.out.println(String.format(
									"    Found update statement, updating block %s:\n\tBLOCK %-10sblock complexity = %-10shighest sub-complexity = %s",
									bS.peek().getName(), bS.peek().getName(), new Complexity(0, 1).toString(),
									bS.peek().getHighestSubComplexity().toString()));
						}
					} else if (line.contains("-=")) {
						bS.peek().setBlockComplexity(new Complexity(1, 0));
						{
							System.out.println(String.format(
									"    Found update statement, updating block %s:\n\tBLOCK %-10sblock complexity = %-10shighest sub-complexity = %s",
									bS.peek().getName(), bS.peek().getName(), new Complexity(1, 0).toString(),
									bS.peek().getHighestSubComplexity().toString()));
						}
					}
				} else {
					continue;
				}
			}
		}

		return bS.peek().getBlockComplexity().addTo(bS.peek().getHighestSubComplexity());
	}

	private String newNameFromCounter() {
		String myresult = "";
		for (int i = 0; i < counterPointer; i++) {
			myresult += counter.get(i) + ".";
		}
		return myresult.substring(0, myresult.length() - 1);

	}

	private String whichKeyword(String line) {
		String[] kW = new String[CodeBlock.BLOCK_TYPES.length];
		for (int i = 0; i < kW.length; i++) {
			kW[i] = CodeBlock.BLOCK_TYPES[i];
			if (CodeBlock.BLOCK_TYPES[i].equals("else")) {
				kW[i] += ":";
			} else {
				kW[i] += " ";
			}
			if (CodeBlock.BLOCK_TYPES[i].equals("def")) {
				continue;
			}
			kW[i] = " " + kW[i];
		}

		for (int i = 0; i < kW.length; i++) {
			if (line.contains(kW[i])) {
				return CodeBlock.BLOCK_TYPES[i];
			}
		}
		// the line following this one actually happened
		// return null; // doesn't happen
		return "";
	}

	private boolean hasKeyword(String line) {
		String[] kW = CodeBlock.BLOCK_TYPES;
		for (String element : kW) {
			if (line.indexOf(element) > -1)
				// System.out.println("line (" + line + ") has a keyword.");
				return true;
		}
		// System.out.println("line (" + line + ") has no keywords.");
		return false;
	}

	private void POP(String[] program, int index) {
		int delta; // number of code blocks to POP
		delta = indentSize(program[index - 1]) - indentSize(program[index]);
		while (delta != 0 && bS.size() != 0) {
			CodeBlock oldTop = bS.pop();
			bS.peek().setHighestSubComplexity(oldTop.getBlockComplexity()
					.addTo(oldTop.getHighestSubComplexity().addTo(bS.peek().getHighestSubComplexity())));
			
			delta--;
			counterPointer--;
			
			// if the top block's sub complexity is smaller, it needs to be updated.
			// def smaller 
			if (bS.peek().getHighestSubComplexity().compareTo(oldTop.getBlockComplexity()) < 0)
			{
				System.out.println(String.format(
						"    Leaving block %s:\n\tBLOCK %-10sblock complexity = %-10shighest sub-complexity = %s",
						oldTop.getName(), oldTop.getName(), new Complexity(0, 0).toString(),
						bS.peek().getHighestSubComplexity().toString()));
			} else {
				System.out.println(String.format(
						"    Leaving block %s, nothing to update.\n\tBLOCK %-10sblock complexity = %-10shighest sub-complexity = %s",
						oldTop.getName(), bS.peek().getName(), new Complexity(0, 0).toString(),
						bS.peek().getHighestSubComplexity().toString()));
			}
		}
		return;
	}

	private boolean prevGtCurrent(String[] program, int index) {
		if (index == 0)
			return false;
		return (indentSize(program[index - 1]) > indentSize(program[index]));
	}

	private int indentSize(String a) {
		char[] b = a.toCharArray();
		int indentSize = 0;
		for (int i = 0; i < b.length; i++) {
			if (b[i] == ' ') {
				indentSize++;
			} else {
				break;
			}
		}
		return indentSize / PythonTracer.SPACE_COUNT;
	}

	public String[] getProgram() {
		return program;
	}

	public void setProgram(String[] program) {
		this.program = program;
	}

}