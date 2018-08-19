package api;

import cli.PythonTracer;

public class Complexity implements Comparable<Complexity> {
	private int n_power;
	private int log_power;

	public Complexity() {
		this.n_power = 0;
		this.log_power = 0;
	}

	public Complexity(int n_power, int log_power) {
		this.n_power = n_power;
		this.log_power = log_power;
	}

	public String toString() {
		if (n_power == log_power && n_power == 0) {
			return "O(1)";
		}
		String powerTerm = (n_power > 0) ? String.format("n^%d", n_power) : "";
		String multiplicationSign = (n_power > 0 && log_power > 0) ? " * " : "";
		String logTerm = (log_power > 0) ? String.format("log(n)^%d", log_power) : "";
		return String.format("O(%s%s%s)", powerTerm, multiplicationSign, logTerm);
	}

	public int getN_power() {
		return n_power;
	}

	public void setN_power(int n_power) {
		this.n_power = n_power;
	}

	public int getLog_power() {
		return log_power;
	}

	public void setLog_power(int log_power) {
		this.log_power = log_power;
	}

	@Override
	public int compareTo(Complexity arg0) {
		if (this.getN_power() == arg0.getN_power()) {
			return Integer.valueOf(this.getLog_power()).compareTo(arg0.getLog_power());
		}
		return Integer.valueOf(this.getN_power()).compareTo(arg0.getN_power());
	}

	// why doesnt java support operator overloading
	public Complexity addTo(Complexity addend) {
		return new Complexity((this.getN_power() + addend.getN_power()), (this.getLog_power() + addend.getLog_power()));
	}
}
