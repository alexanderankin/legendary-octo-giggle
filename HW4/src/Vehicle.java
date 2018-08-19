/**
 * talk about POJO
 * 
 * @author Dave Ankin
 * 110020958
 * david.ankin@stonybrook.edu
 * hw4
 * CSE214
 * Recitation 4
 */
public class Vehicle {
	public static int serialCounter = 0;

	private int serialID;
	private int timeArrived;

	public int getSerialID() { return this.serialID; }
	public int getTimeArrived() { return this.timeArrived; }

	public Vehicle (int initTimeArrived) {
		serialID = serialCounter++;
	}

	public String toString() {
		return "[" + String.format("%03d", serialID) + "]";
	}
}
