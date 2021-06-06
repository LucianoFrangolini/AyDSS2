package front.totem.interfaces;

import java.awt.event.ActionListener;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que ser� implementada por
 *         vistas.<br>
 *         Posee los m�todos necesarios para su m�nimo funcionamiento.<br>
 *
 */
public interface Inscripcion {

	static final String CERO = "0";
	static final String UNO = "1";
	static final String DOS = "2";
	static final String TRES = "3";
	static final String CUATRO = "4";
	static final String CINCO = "5";
	static final String SEIS = "6";
	static final String SIETE = "7";
	static final String OCHO = "8";
	static final String NUEVE = "9";
	static final String BACKSPACE = "eliminar";
	static final String ENVIAR = "enviar";

	/**
	 * M�todo getter del contenido del label del display.<br>
	 * 
	 * @return contenido del label del display del totem.<br>
	 */
	String getLabelDisplay();

	/**
	 * M�todo setter del display del totem.<br>
	 * 
	 * @param texto de tipo String: Representa el texto a mostrar en el label.<br>
	 */
	void setLabelDisplay(String texto);

	/**
	 * M�todo encargado de habilitar el bot�n para enviar informaci�n.<br>
	 */
	void habilitarEnvio();

	/**
	 * M�todo encargado de deshabilitar el bot�n para enviar informaci�n.<br>
	 */
	void deshabilitarEnvio();

	/**
	 * M�todo encargado de setear los action commands.<br>
	 */
	void setActionCommands();
	
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
