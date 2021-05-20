package front.interfaces;

import java.awt.event.ActionListener;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que ser� implementada por
 *         vistas.<br>
 *         Posee los m�todos necesarios para su m�nimo funcionamiento.<br>
 *
 */
public interface IVista {
	
	/**
	 * M�todo encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	void setActionListener(ActionListener c);

	/**
	 * M�todo encargado de abrir una vista.<br>
	 */
	void abrir();
}
