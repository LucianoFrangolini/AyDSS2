package back.servidor.colaDeEspera;

import back.servidor.Cliente;

public class ColaDeEsperaFIFOStrategy extends ColaDeEspera {

	private static final long serialVersionUID = -7398655030654309691L;

	/**
	 * Método encargado de obtener el proximo valor de la cola de espera.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.<br>
	 */
	@Override
	public Cliente obtenerSiguiente() {
		return this.cola.poll();
	}
}
