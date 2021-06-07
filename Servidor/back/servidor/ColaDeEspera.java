package back.servidor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import back.servidor.interfaces.IColaStrategy;

/**
 * @author Grupo12 <br>
 *         Clase para la cola de espera que encapsula el uso de un Queue. <br>
 */
public abstract class ColaDeEspera implements IColaStrategy, Serializable{

	private static final long serialVersionUID = -8359156628507108488L;
	protected Queue<Cliente> cola = new LinkedList<Cliente>();

	/**
	 * Método encargado de agregar un elemento la cola de espera.<br>
	 * <b> Pre: </b> el String de dni enviado debe ser distinto de null.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.<br>
	 */
	public void add(Cliente cliente) {
		if(!this.contains(cliente))
			this.cola.add(cliente);
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
	public Boolean contains(Cliente cliente) {
		Boolean res = false;
		Iterator<Cliente> it = cola.iterator();
		while(it.hasNext()) {
			String aux = it.next().getDni();
			if(aux.equals(cliente.getDni()))
				res = true;
		}
		return res;
	}
}
