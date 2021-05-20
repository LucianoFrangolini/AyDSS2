package back.servidor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Grupo12 <br>
 *         Clase para la cola de espera que encapsula el uso de un Queue. <br>
 */
public class ColaDeEspera {
	private Queue<String> cola = new LinkedList<String>();

	/**
	 * Método encargado de agregar un elemento la cola de espera.<br>
	 * <b> Pre: </b> el String de dni enviado debe ser distinto de null.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.<br>
	 */
	public void add(String dni) {
		this.cola.add(dni);
	}

	/**
	 * Método encargado de obtener el proximo valor de la cola de espera.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.<br>
	 */
	public String poll() {
		return this.cola.poll();
	}

	/**
	 * Método encargado de revisar si un elemento se encuentra en la cola de
	 * espera.<br>
	 * 
	 * <b> Pre: </b> el String de dni enviado debe ser distinto de null.<br>
	 * 
	 * @return devuelve true si el elemento se encontraba en la cola, false en caso
	 *         contrario.<br>
	 */
	public Boolean contains(String dni) {
		return this.cola.contains(dni);
	}
}
