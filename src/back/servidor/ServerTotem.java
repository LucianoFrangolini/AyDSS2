package back.servidor;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import back.conexiones.ConexionSocket;

public class ServerTotem implements Runnable {

	private Administrador admin;
	private int puerto;
	private Boolean detener;
	
	private ServerSocket totemServerSocket;
	private Socket socket;
	
	private Thread server;
	

	public ServerTotem(Administrador admin, int puerto) {
		this.admin = admin;
		this.puerto = puerto;
		this.detener = false;
		this.server = new Thread(this,String.valueOf(puerto));
	}

	@Override
	public void run() {
		BufferedReader myInput;
		PrintWriter myOutput;
		String nuevoDni = null;

		try {
			totemServerSocket = new ServerSocket(this.puerto);
			while (!this.detener) {
				socket = totemServerSocket.accept();
				myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				myOutput = new PrintWriter(socket.getOutputStream(), true);
				nuevoDni = myInput.readLine();
				if (!this.detener) {
					if (this.admin.agregarDni(nuevoDni))
						myOutput.println("Registro exitoso");
					else
						myOutput.println("El DNI ya se encuentra registrado.");
					myInput.close();
					myOutput.close();
					socket.close();
				} else {
					myInput.close();
					myOutput.close();
					totemServerSocket.close();
				}
			}
		} catch (IOException e) {
		}
	}

	public void cambiarEstado() {
		if (this.detener)
			this.detener = false;
		else {
			try {
				if (this.socket==null)
					this.totemServerSocket.close();
				else {
					this.socket.close();
					this.totemServerSocket.close();
				}
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
