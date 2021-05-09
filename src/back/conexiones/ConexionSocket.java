package back.conexiones;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Grupo12 <br>
 *         Clase abstracta que contiene los atributos para una Conexion a traves
 *         de Socket. <br>
 */
public abstract class ConexionSocket {

	protected static String MENSAJE_SIN_CONEXION = "No se pudo establecer una conexion con el servidor";

	protected String host;
	protected int puerto;
	protected BufferedReader myInput = null;
	protected PrintWriter myOutput = null;
	protected Socket socket;
}
