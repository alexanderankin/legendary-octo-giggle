import java.util.Random;

public class DataHoldingClass {

	private int numeral;
	private String letters;
	private int number;

	public void randomizeContents() {
		Random a = new Random();
		this.number = a.nextInt(100);
		this.numeral = a.nextInt(10);
		
		int stringValue = 65 + a.nextInt(25);
		int length = 1 + a.nextInt(5);
		this.letters = "";
		for (int i = 0; i < length; i++) {
			letters += (char) stringValue;
		}
	}
	
	public int sum () {
		return number + numeral;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumeral() {
		return numeral;
	}

	public void setNumeral(int numeral) {
		this.numeral = numeral;
	}

	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}

}
