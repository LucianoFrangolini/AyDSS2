package back.servidor.colaDeEspera;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import back.servidor.componente.Cliente;
import back.servidor.interfaces.IColaStrategy;

/**
 * @author Grupo12 <br>
 *         Clase para la cola de espera que encapsula el uso de un Queue. <br>
 */
public abstract class ColaDeEspera implements IColaStrategy, Serializable{

	private static final long serialVersionUID = -8359156628507108488L;
	protected LinkedList<Cliente> cola = new LinkedList<Cliente>();

	/**
	 * Método encargado de agregar un elemento la cola de espera.<br>
	 * <b> Pre: </b> el cliente de dni enviado debe ser distinto de null y no debe encontrarse ya en la cola.<br>
	 * <b> Post: </b> se agrega un cliente a la cola de espera.<br>
	 */
	public void add(Cliente cliente) {
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
	public Boolean contains(String dni) {
		Boolean res = false;
		Iterator<Cliente> it = cola.iterator();
		while(it.hasNext()) {
			String aux = it.next().getDni();
			if(aux.equals(dni))
				res = true;
		}
		return res;
	}
	
	public void actualizarCliente(Cliente nuevoCliente,Cliente viejoCliente) {
		int posicion = this.cola.indexOf(viejoCliente);
		this.cola.set(posicion, nuevoCliente);
	}
	
	public Iterator<Cliente> getIteradorClientes(){
		return this.cola.iterator();
	}
}
