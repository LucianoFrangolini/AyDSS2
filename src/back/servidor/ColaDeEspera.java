package back.servidor;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ColaDeEspera {
	//private PriorityQueue<String> cola = new PriorityQueue<String>();
	private Queue<String> cola = new LinkedList<String>();
	
	public void add(String dni) {
		this.cola.add(dni);
	}
	public String poll() {
		return this.cola.poll();
	}
	public Boolean contains(String dni) {
		return this.cola.contains(dni);
	}
	
	/*
	public peek(): This method retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	public poll(): This method retrieves and removes the head of this queue, or returns null if this queue is empty.
	*/
}
