package main;

import back.servidor.Administrador;
import front.servidor.interfaces.IVista;
import front.servidor.vista.VistaServidor;

public class Servidor1 {

	/**
	 * Método encargado de ejecutar el Servidor.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {
		
		Administrador administrador = new Administrador("Servidor1");
		IVista vista = new VistaServidor("Servidor 1");
		administrador.abrirServidor();
		vista.abrir();
		

	}
}
