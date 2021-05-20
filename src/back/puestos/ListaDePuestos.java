package back.puestos;

import java.util.HashMap;

/**
 * @author Grupo12 <br>
 *         Clase para la lista de puestos que encapsula el uso de un Hashmap.
 *         <br>
 */
public class ListaDePuestos {
	private HashMap<Integer, Puesto> puestos = new HashMap<Integer, Puesto>();

	/**
	 * Método encargado de agregar un puesto y devolverlo.<br>
	 * 
	 * @return la instancia de Puesto creado.
	 */
	public Puesto agregarPuesto(int numeroPuesto) {
		Puesto puesto = new Puesto(numeroPuesto);
		this.puestos.put(numeroPuesto, puesto);
		return puesto;
	}

	/**
	 * Método encargado de eliminar el puesto solicitado a traves del número que lo
	 * identifica.<br>
	 * <b> Post: </b> Se elimina el puesto solicitado si es que existia.<br>
	 */
	public void eliminarPuesto(int numeroPuesto) {
		this.puestos.remove(numeroPuesto);
	}

	/**
	 * Método encargado de devolver un puesto pedido.<br>
	 * 
	 * @return la instancia de Puesto pedida, o null si no existe.
	 */
	public Puesto getPuesto(int numeroPuesto) {
		return this.puestos.get(numeroPuesto);
	}
}
