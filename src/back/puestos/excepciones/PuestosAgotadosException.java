package back.puestos.excepciones;

public class PuestosAgotadosException extends Exception {

	private static final long serialVersionUID = 4007313154395495232L;

	public PuestosAgotadosException(String mensaje) {
		super(mensaje);
	}
}
