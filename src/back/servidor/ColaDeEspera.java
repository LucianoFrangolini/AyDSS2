package back.servidor;

import java.util.LinkedList;
import java.util.Queue;

public class ColaDeEspera {
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
}
