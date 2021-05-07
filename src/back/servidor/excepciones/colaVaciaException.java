package back.servidor.excepciones;

//NO SE USA 



/**
 * @author Grupo12
 *<br>
 *Excepcion para el caso de un pedido de siguiente cliente a una cola vacia.
 */
@SuppressWarnings("serial")
public class colaVaciaException extends Exception {
	/**
	 * Metodo que se activa cuando se lanza la excepcion
	 * @param mensaje: parametro de tipo String que representa el mensaje del error
	 */
	public colaVaciaException(String mensaje) {
		super(mensaje);
	}
}
