package front.display.interfaces;

import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que será implementada por
 *         vistas.<br>
 *         Posee los métodos necesarios para su mínimo funcionamiento.<br>
 *
 */
public interface IVistaDisplay{

	/**
	 * Método encargado de actualizar la lista de Turnos en el display<br>
	 * <b> Post: </b> Se actualiza la lista visible en el display.<br>
	 * 
	 * @param it de tipo Iterator<Entry<Integer,String>>: Representa el iterador de
	 *           la lista a actualizar.<br>
	 */
	void actualizarLista(Iterator<Entry<Integer, String>> it);
	
	/**
	 * Método encargado de abrir una vista.<br>
	 */
	void abrir();
}
