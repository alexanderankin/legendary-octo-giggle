
public class Thing {
	public static void main(String[] args) {
		int thing = -1;
		try {
			thing = parseBinary("0101");
		} catch (thingException e) {
			// TODO: handle exception
		}
		System.out.println(thing);
	}

	/**
	 * given a binary string, returns its int value (base 10)
	 * 
	 * @param a
	 *            a binary string
	 * @return integer value of base 10
	 * @throws thingException
	 */
	private static int parseBinary(String a) throws thingException {
		for (char c : a.toCharArray()) {
			if (c != '0' && c != '1')
				throw new thingException();
		}
		return parseBinary(a, 0);
	}

	/**
	 * Helper method with additional sum argument
	 * 
	 * @param a
	 *            String from other method
	 * @param sum
	 * @return
	 */
	private static int parseBinary(String a, int sum) {
		if (a.length() == 0)
			return sum;
		return parseBinary(a.substring(1),
				((sum + Integer.valueOf(a.substring(0, 1)) * ((int) Math.pow(2.0, (double) a.length() - 1)))));
	}
}
