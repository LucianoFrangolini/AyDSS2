package front.interfaces;

/**
 * @author Grupo12<br>
 *         * Clase que representa una interfaz que será implementada por
 *         vistas.<br>
 *         Posee los métodos necesarios para su mínimo funcionamiento.<br>
 *
 */
public interface Inscripcion extends IVista {

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
	 * Método getter del contenido del label del display.<br>
	 * 
	 * @return contenido del label del display del totem.<br>
	 */
	String getLabelDisplay();

	/**
	 * Método setter del display del totem.<br>
	 * 
	 * @param texto de tipo String: Representa el texto a mostrar en el label.<br>
	 */
	void setLabelDisplay(String texto);

	/**
	 * Método encargado de habilitar el botón para enviar información.<br>
	 */
	void habilitarEnvio();

	/**
	 * Método encargado de deshabilitar el botón para enviar información.<br>
	 */
	void deshabilitarEnvio();

	/**
	 * Método encargado de setear los action commands.<br>
	 */
	void setActionCommands();
}
