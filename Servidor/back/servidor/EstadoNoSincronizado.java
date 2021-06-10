package back.servidor;

import back.servidor.interfaces.Redundancia;


public class EstadoNoSincronizado implements Redundancia {
Administrador admin;
	
	public EstadoNoSincronizado(Administrador admin) {
		this.admin = admin;
	}
	
	@Override
	public void backup() {
		//Este estado no hace backups
	}
	
	/**
	 * Método para cubrir la implementacion de la interfaz en un estado que no requiere sincronizar<br>
	 */
	@Override
	public void intentarSincronizar() {
		//Este estado no sincroniza
	}
}
