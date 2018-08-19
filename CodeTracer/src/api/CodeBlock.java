package api;

public class CodeBlock {
	public final static String[] BLOCK_TYPES = { "def", "for", "while", "else", "if", "elif" };
	public final static int DEF = 0;
	public final static int FOR = 1;
	public final static int WHILE = 2;
	public final static int IF = 3;
	public final static int ELIF = 4;
	public final static int ELSE = 5;

	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	
	private String name;
	private String loopVariable;
	
	private int type;
	
	public CodeBlock() {
		this.blockComplexity = new Complexity(0, 0);
		this.highestSubComplexity = new Complexity(0, 0);
	}

	public CodeBlock(Complexity bC, Complexity hSC) {
		this.blockComplexity = bC;
		this.highestSubComplexity = hSC;
	}

	public Complexity getBlockComplexity() {
		return blockComplexity;
	}

	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}

	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}

	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}

	public String getName() {
		return name;
	}

	public CodeBlock setName(String name) {
		this.name = name;
		return this;
	}

	public String getLoopVariable() {
		return loopVariable;
	}

	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}

	public int getType() {
		return type;
	}

	public CodeBlock setType(int type) {
		this.type = type;
		return this;
	}
	
	public String toString () {
		return type + " type Code Block";
	}
}
