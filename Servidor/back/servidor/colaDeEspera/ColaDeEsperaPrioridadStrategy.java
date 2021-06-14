package back.servidor.colaDeEspera;

import java.util.Iterator;

import libreria.clasescompartidas.Cliente;

/**
 * @author Grupo 12 <br>
 *         Esta clase se encarga de definir el comportamiento de la cola. <br>
 *         Saldra primero de la cola aquel Cliente con mayor prioridad.<br>
 *         Es utilizado para implementar el patron Strategy.
 */
public class ColaDeEsperaPrioridadStrategy extends ColaDeEspera {

	private static final long serialVersionUID = -1777719770132422628L;

	/**
	 * Método encargado de obtener el proximo cliente de la cola de espera.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.<br>
	 */
	@Override
	public Cliente obtenerSiguiente() {
		Iterator<Cliente> it = cola.iterator();
		int maxPrio = -1;
		Cliente maxPrioCliente = null;
		while (it.hasNext()) {
			Cliente auxCliente = it.next();
			if (auxCliente.getPrioridad() > maxPrio) {
				maxPrio = auxCliente.getPrioridad();
				maxPrioCliente = auxCliente;
			}
		}
		this.cola.remove(maxPrioCliente);
		return maxPrioCliente;
	}

}
