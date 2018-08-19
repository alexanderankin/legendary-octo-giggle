package api;

import java.util.ArrayList;

public class BlockStack extends java.util.Stack<CodeBlock> {
	private static final long serialVersionUID = 768341743941991066L;

	private static int lastPlace(String a) {
		int lastDec = a.lastIndexOf(".") + 1;
		return Integer.valueOf(a.substring(lastDec, a.length()));
	}

	public static String lastOfNumber(ArrayList<String> hist, int len) {
		String result = null;
		int decimalCt = len - 1;
		for (String h : hist) {
			if (decimalCount(h) == decimalCt)
				result = h;
		}
		return result;
	}

	public static String nextNumberIfRetxning(ArrayList<String> hist) {
		return hist.get(hist.size() - 1) + "." + String.valueOf(1 + lastPlace(hist.get(hist.size() - 2)));
	}

	// checks if history contains an element one bigger than prev (meaning the
	// next one has to increment from it)
	public static boolean returning(ArrayList<String> hist) {
		String last = hist.get(hist.size() - 1);
		int decL = decimalCount(last);
		for (String string : hist) {
			if (decimalCount(string) > decL)
				return true;
		}
		return false;
	}

	public static int decimalCount(String a) {
		int decimals = 0;
		char[] chars = a.toCharArray();
		for (char c : chars) {
			if (c == '.')
				decimals++;
		}
		return decimals;
	}

}
