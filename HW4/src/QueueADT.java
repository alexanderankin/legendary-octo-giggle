/**
 * This is a queue interface basically
 * 
 * @author Dave Ankin
 * 110020958
 * david.ankin@stonybrook.edu
 * hw4
 * CSE214
 * Recitation 4
 */
public interface QueueADT {
	/**
	 * 
	 */
	public void enqueue(Vehicle vehicle);
	
	public Vehicle dequeue();

	public int size();

	public boolean isEmpty();
}
