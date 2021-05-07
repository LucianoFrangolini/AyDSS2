package back.conexiones;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class ConexionSocket {

	protected String host;
	protected int puerto;
	protected BufferedReader myInput = null;
	protected PrintWriter myOutput = null;
	protected Socket socket;
}
