package back.servidor.colaDeEspera;

import java.util.Iterator;

import libreria.clasescompartidas.Cliente;

/**
 * @author Grupo 12 <br>
 *         Esta clase se encarga de definir el comportamiento de la cola. <br>
 *         Saldra primero aquel Cliente con menor numero de DNI.<br>
 *         Es utilizado para implementar el patron Strategy.
 */
public class ColaDeEsperaAscendenteStrategy extends ColaDeEspera {

	private static final long serialVersionUID = -7435996972220484293L;

	/**
	 * Método encargado de obtener el proximo cliente de la cola de espera.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.<br>
	 */
	@Override
	public Cliente obtenerSiguiente() {
		Iterator<Cliente> it = cola.iterator();
		int minDni = 100000000;
		Cliente minDniCliente = null;
		while (it.hasNext()) {
			Cliente auxCliente = it.next();
			int dniCliente = Integer.parseInt(auxCliente.getDni());
			if (dniCliente < minDni) {
				minDni = dniCliente;
				minDniCliente = auxCliente;
			}
		}
		this.cola.remove(minDniCliente);
		return minDniCliente;
	}

}
