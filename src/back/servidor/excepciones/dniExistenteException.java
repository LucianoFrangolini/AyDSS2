package back.servidor.excepciones;

/**
 * @author Grupo12
 *<br>
 *Excepcion para el caso de DNI existente
 */
@SuppressWarnings("serial")
public class dniExistenteException extends Exception {
	/**
	 * Metodo que se activa cuando se lanza la excepcion
	 * @param mensaje: parametro de tipo String que representa el mensaje del error
	 */
	public dniExistenteException(String mensaje) {
		super(mensaje);
	}
}
