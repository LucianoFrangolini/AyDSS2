package back.servidor.colaDeEspera;

import libreria.clasescompartidas.Cliente;

/**
 * @author Grupo 12 <br>
 *         Esta clase se encarga de definir el comportamiento de la cola. <br>
 *         Saldra primero de la cola aquel Cliente que haya entrado antes.<br>
 *         Es utilizado para implementar el patron Strategy.
 */
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
