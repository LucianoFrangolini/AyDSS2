package back.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import back.constantes.ListaDeAcciones;

public class ServerPuestos implements Runnable {

	private Administrador admin;
	private int puerto;
	private Boolean detener;
	private Thread server;
	private ServerSocket puestosServerSocket;
	private Socket socket;

	public ServerPuestos(Administrador admin, int puerto) {
		this.admin = admin;
		this.puerto = puerto;
		this.detener = false;
		this.server = new Thread(this, String.valueOf(puerto));
	}

	@Override
	public void run() {

		BufferedReader myInput;
		PrintWriter myOutput;
		int numeroPuesto;
		String dni, accion;

		try {
			puestosServerSocket = new ServerSocket(this.puerto);

			while (!detener) {

				socket = puestosServerSocket.accept();
				myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				myOutput = new PrintWriter(socket.getOutputStream(), true);

				accion = myInput.readLine();
				if (!this.detener) {
					if (accion.equals(ListaDeAcciones.ABRIR_PUESTO)) {
						int nuevoNumPuesto = buscaYOcupaPuesto();
						if (nuevoNumPuesto != 0)
							myOutput.println(nuevoNumPuesto);
						else
							myOutput.println("error");
					} else {

						numeroPuesto = Integer.parseInt(myInput.readLine());

						if (accion.equals(ListaDeAcciones.LLAMAR_CLIENTE)) {
							dni = admin.obtenerProximoCliente();
							if (admin.agregarTurno(numeroPuesto, dni)) {
								myOutput.println(dni);
							} else
								myOutput.println("No hay clientes en espera");

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
				} else {
					myInput.close();
					myOutput.close();
					puestosServerSocket.close();
				}

			}

			puestosServerSocket.close();

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

	public void cambiarEstado() {
		if (this.detener)
			this.detener = false;
		else {
			try {
				if (this.socket == null)
					this.puestosServerSocket.close();
				else
					this.socket.close();
				this.detener = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		this.server.start();
	}

}
