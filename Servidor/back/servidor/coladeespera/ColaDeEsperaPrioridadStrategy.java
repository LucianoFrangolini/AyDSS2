package back.servidor.coladeespera;

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
