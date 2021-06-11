package back.servidor.interfaces;

/**
 * @author Grupo12 <br>
 *         Interfaz para la redundancia que implementa el patr�n State. <br>
 */
public interface Redundancia {

	void intentarSincronizar();
	void backup();
}
