/**
 * Talk about fancy tostring methods, amirite?
 * 
 * @author Dave Ankin
 * 110020958
 * david.ankin@stonybrook.edu
 * hw4
 * CSE214
 * Recitation 4
 */
import java.util.*;
public class VehicleQueue implements QueueADT {
	private LinkedList<Vehicle> list;// = new LinkedList<>();

	public void enqueue(Vehicle vehicle) {
		list.add(vehicle);
	}
	
	public VehicleQueue () {
		this.list = new LinkedList<>();
	}
	
	public Vehicle dequeue() {
		Vehicle result = null;
		try {
			result = list.poll();
		} catch (Exception e) {
			System.out.println("i caught a fucking exception");
			result = null;
		}
		return result;
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return (list.size() == 0);
	}

	public String toString(boolean forwards) {
		LinkedList<String> vStrings = new LinkedList<>();
		for (Vehicle v : list) {
			if (forwards) vStrings.addLast(v.toString());
			else vStrings.addFirst(v.toString());
		}

		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(String s : vStrings){
			if(!first || (first = false)) sb.append(" ");
			sb.append(s);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			VehicleQueue q = new VehicleQueue();
			q.enqueue(new Vehicle(0));
			q.enqueue(new Vehicle(0));
			System.out.println(q.toString(true));
			System.out.println(q.toString(false));
		} catch (Exception e) {
			
		}
	}

}
