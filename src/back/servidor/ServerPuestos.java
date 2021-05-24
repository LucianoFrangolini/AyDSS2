package back.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import back.constantes.ListaDeAcciones;
import back.constantes.ListaDeMensajes;

public class ServerPuestos implements Runnable {

	private Administrador admin;
	private int puerto;
	private Thread server;

	public ServerPuestos(Administrador admin, int puerto) {
		this.admin = admin;
		this.puerto = puerto;
		this.server = new Thread(this, String.valueOf(puerto));
	}

	@Override
	public void run() {

		ServerSocket puestosServerSocket;
		Socket socket;
		BufferedReader myInput;
		PrintWriter myOutput;
		int numeroPuesto;
		String dni, accion;

		try {
			puestosServerSocket = new ServerSocket(this.puerto);

			while (true) {

				socket = puestosServerSocket.accept();
				myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				myOutput = new PrintWriter(socket.getOutputStream(), true);

				accion = myInput.readLine();

				if (accion.equals(ListaDeAcciones.ABRIR_PUESTO)) {
					int nuevoNumPuesto = buscaYOcupaPuesto();
					if (nuevoNumPuesto != 0)
						myOutput.println(nuevoNumPuesto);
					else
						myOutput.println(ListaDeMensajes.ERROR);
				} else {

					numeroPuesto = Integer.parseInt(myInput.readLine());

					if (accion.equals(ListaDeAcciones.LLAMAR_CLIENTE)) {
						dni = admin.obtenerProximoCliente();
						if (admin.agregarTurno(numeroPuesto, dni)) {
							myOutput.println(dni);
						} else
							myOutput.println(ListaDeMensajes.SIN_CLIENTES);

					} else if (accion.equals(ListaDeAcciones.ELIMINAR_TURNO)) {
						admin.eliminarTurno(numeroPuesto);

					} else if (accion.equals(ListaDeAcciones.CERRAR_PUESTO)) {
						admin.eliminarTurno(numeroPuesto);
						admin.getPuestosDeTrabajo()[numeroPuesto - 1] = 0;
					}
				}
				myInput.close();
				myOutput.close();
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	private int buscaYOcupaPuesto() {
		int i = 0;
		int[] puestos = admin.getPuestosDeTrabajo();

		while (i < puestos.length && puestos[i] != 0)
			i++;
		if (i < puestos.length)
			puestos[i] = 1;
		else
			i = -1;

		return i + 1;
	}

	public void start() {
		this.server.start();
	}

}
