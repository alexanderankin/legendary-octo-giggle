/**
 * This class is too cool for school. it gives me a method
 * so i dont have to call Math function. awesome sauce.
 * 
 * @author Dave Ankin
 * 110020958
 * david.ankin@stonybrook.edu
 * hw4
 * CSE214
 * Recitation 4
 */
class BooleanSource {
	private double probability;
	public double getProbability () { return probability; }
	public void setProbability (double initProbability) throws IllegalArgumentException {
		if (initProbability <= 0 || initProbability > 1) {
			throw new IllegalArgumentException();
		}
		probability = initProbability;
	}

	/*public BooleanSource() {}*/
	public BooleanSource (double initProbability) throws IllegalArgumentException {
		this.setProbability(initProbability);
	}

	/**
	 * returns true with instantiated probability
	 * 
	 * uses Math.random
	 * @return whether or not event occurred.
	 */
	public boolean occurs() {
		return Math.random() < this.probability;
	}
}
