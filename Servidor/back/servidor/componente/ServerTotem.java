package back.servidor.componente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import libreria.clasescompartidas.ListaDeMensajes;

public class ServerTotem implements Runnable {

	private Administrador admin;
	private int puerto;

	private Thread server;

	public ServerTotem(Administrador admin, int puerto) {
		this.admin = admin;
		this.puerto = puerto;
		this.server = new Thread(this, String.valueOf(puerto));
	}

	@Override
	public void run() {
		
		ServerSocket totemServerSocket;
		
		Socket socket;
		BufferedReader myInput;
		PrintWriter myOutput;
		
		String nuevoDni = null;
		
		try {
			totemServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = totemServerSocket.accept();
				myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				myOutput = new PrintWriter(socket.getOutputStream(), true);
				nuevoDni = myInput.readLine();
				if (this.admin.agregarDni(nuevoDni))
					myOutput.println(ListaDeMensajes.REGISTRO_EXITOSO);
				else
					myOutput.println(ListaDeMensajes.DNI_EXISTENTE);
				myInput.close();
				myOutput.close();
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	public void start() {
		this.server.start();
	}

}
