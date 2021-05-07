package main;

import back.servidor.AdministradorDeTurnos;
import back.servidor.ConexionConServerSocket;

public class Servidor {

	public static void main(String[] args) {
		
		ConexionConServerSocket servidor = AdministradorDeTurnos.getInstance();
		servidor.abrirServidor();
	}
}
