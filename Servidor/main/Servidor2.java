package main;

import back.servidor.componente.Administrador;
import front.servidor.interfaces.IVista;
import front.servidor.vistas.VistaServidor;

public class Servidor2 {

	/**
	 * Método encargado de ejecutar el Servidor.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {
		
		Administrador administrador = new Administrador("Servidor2");
		IVista vista = new VistaServidor("Servidor 2");
		administrador.abrirServidor();
		vista.abrir();
		
	}
}
