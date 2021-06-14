package back.servidor.componente;

import back.servidor.interfaces.Redundancia;

/**
 * @author Grupo 12 La Clase EstadoNoSincronizado es utilizada para implementar
 *         el patron State, </br>
 *         en este estado no se realiza backup ni se sincroniza.
 */
public class EstadoNoSincronizado implements Redundancia {
	Administrador admin;

	/**
	 * Contructor de EstadoNoSincronizado
	 * 
	 * @param admin referencia al mismo administrador que lo contiene.
	 */
	public EstadoNoSincronizado(Administrador admin) {
		this.admin = admin;
	}

	/**
	 * Método encargado de cubrir la implementacion de la interfaz en un estado que
	 * no requiere backup<br>
	 */
	@Override
	public void backup() {
		// Este estado no hace backups
	}

	/**
	 * Método para cubrir la implementacion de la interfaz en un estado que no
	 * requiere sincronizar<br>
	 */
	@Override
	public void intentarSincronizar() {
		// Este estado no sincroniza
	}
}
