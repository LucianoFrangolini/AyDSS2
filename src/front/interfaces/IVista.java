package front.interfaces;

import java.awt.event.ActionListener;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que será implementada por
 *         vistas.<br>
 *         Posee los métodos necesarios para su mínimo funcionamiento.<br>
 *
 */
public interface IVista {
	
	/**
	 * Método encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	void setActionListener(ActionListener c);

	/**
	 * Método encargado de abrir una vista.<br>
	 */
	void abrir();
}
