package front.puesto.interfaces;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que ser? implementada por
 *         vistas.<br>
 *         Posee los m?todos necesarios para su m?nimo funcionamiento.<br>
 *
 */
public interface IVistaPuesto{

	static final String LLAMAR = "Llamar";
	static final String ELIMINAR = "Eliminar";

	/**
	 * M?todo encargado de mostrar el cliente al que el puesto se encuentra
	 * atendiendo en el label correspondiente.<br>
	 * 
	 * <b> Post: </b> Se muestra el DNI del cliente en el label correspondiente.<br>
	 * 
	 * @param clienteActual de tipo String: Representa el DNI del cliente al cual se
	 *                      atiende.<br>
	 */
	void setDisplay(String clienteActual);

	/**
	 * M?todo encargado de devolver el n?mero del puesto<br>
	 * 
	 * @return n?mero del puesto.<br>
	 */
	int getNumeroPuesto();

	/**
	 * M?todo encargado de habilitar el bot?n para eliminar un cliente del puesto.<br>
	 */
	void habilitarBotonEliminar();

	/**
	 * M?todo encargado de deshabilitar el bot?n para eliminar un cliente del puesto<br>
	 */
	void deshabilitarBotonEliminar();

	/**
	 * M?todo encargado de eliminar la vista del puesto<br>
	 */
	void dispose();
	
	void setNumeroPuesto(int numero);
	
	PropertyChangeSupport getPropertyChangeSupport();
	
	/**
	 * M?todo encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	void setActionListener(ActionListener c);

	/**
	 * M?todo encargado de abrir una vista.<br>
	 */
	void abrir();
}
