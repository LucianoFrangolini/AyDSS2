package front.interfaces;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que será implementada por
 *         vistas.<br>
 *         Posee los métodos necesarios para su mínimo funcionamiento.<br>
 *
 */
public interface IVistaPuesto extends IVista {

	static final String LLAMAR = "Llamar";
	static final String ELIMINAR = "Eliminar";

	/**
	 * Método encargado de mostrar el cliente al que el puesto se encuentra
	 * atendiendo en el label correspondiente.<br>
	 * 
	 * <b> Post: </b> Se muestra el DNI del cliente en el label correspondiente.<br>
	 * 
	 * @param clienteActual de tipo String: Representa el DNI del cliente al cual se
	 *                      atiende.<br>
	 */
	void setDisplay(String clienteActual);

	/**
	 * Método encargado de devolver el número del puesto<br>
	 * 
	 * @return número del puesto.<br>
	 */
	int getNumeroPuesto();

	/**
	 * Método encargado de habilitar el botón para eliminar un cliente del puesto.<br>
	 */
	void habilitarBotonEliminar();

	/**
	 * Método encargado de deshabilitar el botón para eliminar un cliente del puesto<br>
	 */
	void deshabilitarBotonEliminar();

	/**
	 * Método encargado de eliminar la vista del puesto<br>
	 */
	void dispose();
}
