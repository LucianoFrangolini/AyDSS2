package front.interfaces;

import java.util.Iterator;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que ser� implementada por
 *         vistas.<br>
 *         Posee los m�todos necesarios para su m�nimo funcionamiento.<br>
 *
 */
public interface IVistaAdministradorPuestos extends IVista {

	static final String ABRIR_PUESTO = "AbrirPuesto";
	static final String ELIMINAR_PUESTO = "EliminarPuesto";
	static final String AGREGAR_PUESTO = "AgregarPuesto";

	/**
	 * M�todo encargado de actualizar la lista de puestos.<br>
	 * <b> Post: </b> Se actualiza la lista de puestos de la vista.<br>
	 * 
	 * @param it de tipo Iterator<IVistaPuesto>: Representa el iterador de la lista
	 *           de puestos.<br>
	 */
	void actualizarLista(Iterator<IVistaPuesto> it);

	/**
	 * M�todo encargado de obtener la vista del puesto seleccionado de una lista.<br>
	 * 
	 * @return la vista del puesto seleccionado.<br>
	 */
	IVistaPuesto getPuestoSeleccionado();
}
