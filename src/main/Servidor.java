package main;

import back.servidor.AdministradorDeTurnos;
import front.interfaces.IVista;
import front.vistas.VistaServidor;

public class Servidor {

	/**
	 * Método encargado de ejecutar el Servidor.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		AdministradorDeTurnos servidor = AdministradorDeTurnos.getInstance();
		IVista vista = new VistaServidor();
		vista.abrir();
		servidor.abrirServidor();
	}
}
