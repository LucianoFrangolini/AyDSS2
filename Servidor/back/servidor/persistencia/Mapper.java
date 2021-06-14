package back.servidor.persistencia;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import libreria.clasescompartidas.Cliente;

/**
 * @author Grupo12 <br>
 *         La Clase Mapper encapsula la persistencia y facilita la futura.</br>
 *         diversificacion de los metodos para realizarla.</br>
 *         Implementa el patron del mismo nombre.
 */
public class Mapper {

	private final String persistirTXT = "TXT";

	@SuppressWarnings("rawtypes")
	private IPersistencia persistidor;
	private String tipoDePersistencia;
	private String rutaIngresos;
	private String rutaAtendidos;
	private String rutaRegistros;

	// Se pueden agregar más persistidores de distintos tipos
	// Asi como distintos tipos de persistencias

	public Mapper() {
		cargarPropiedades();
		definirPersistencia();
	}

	/**
	 * Metodo encargado de cargar desde un archivo de properties la configuracion
	 * para la<br/>
	 * persistencia(tipo de persistencia, rutas a archivos).
	 * 
	 */
	private void cargarPropiedades() {
		try {
			Properties p = new Properties();
			p.load(new FileReader("Libreria/propiedades/persistencia.properties"));
			this.tipoDePersistencia = p.getProperty("TIPO_DE_PERSISTENCIA");
			this.rutaIngresos = p.getProperty("RUTA_DE_PERSISTENCIA_INGRESOS");
			this.rutaAtendidos = p.getProperty("RUTA_DE_PERSISTENCIA_ATENDIDOS");
			this.rutaRegistros = p.getProperty("RUTA_DE_REGISTRO_DE_CLIENTES");
		} catch (FileNotFoundException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "No se encontró el archivo de configuración de persistencia");
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					"Se encontró un problema leyendo el archivo de configuración de persistencia");
		}
	}

	/**
	 * Selecciona el metodo para persistir a partir de
	 * "persistencia.properties,"</br>
	 * por ahora solamente implementado TXT
	 * 
	 */
	private void definirPersistencia() {
		switch (this.tipoDePersistencia) {
		case persistirTXT:
			this.persistidor = new PersistenciaTXT();
			break;
		}
	}

	/**
	 * Persiste el DNI y momento de registro.
	 * 
	 * @param dni DNI de Cliente
	 */
	public void persistir(String dni) {
		Registro reg = new Registro(dni);
		if (tipoDePersistencia.equalsIgnoreCase(persistirTXT))
			;
		persistir(reg, this.rutaIngresos);
	}

	public void persistir(Cliente cliente, int numPuesto) {
		String nombre = cliente.getNombre();
		if (nombre == null)
			nombre = "No registrado";
		ClienteAtendido clienteAtendido = new ClienteAtendido(nombre, cliente.getDni(), numPuesto);
		if (tipoDePersistencia.equalsIgnoreCase(persistirTXT))
			persistir(clienteAtendido, this.rutaAtendidos);
	}

	/**
	 * Metodo generico encargado de persistir un objeto, </br>
	 * variara su implementacion dependiendo de la configuracion usada
	 * 
	 * @param obj     Objeto cual se quiere persistir.
	 * @param archivo ruta al archivo donde se persistira.
	 */
	@SuppressWarnings("unchecked")
	private void persistir(Object obj, String archivo) {
		try {
			this.persistidor.abrirOutput(archivo);
			this.persistidor.escribir(obj);
			this.persistidor.cerrarOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de buscar un cliente en el archivo referenciado en
	 * propiedades.
	 * 
	 * @param dni DNI del cliente buscado
	 * @return Cliente cuyo DNI coincida, o en caso de no encontrarse, null.
	 */
	public Cliente buscarCliente(String dni) {
		Cliente cliente = null;
		try {
			this.persistidor.abrirInput(this.rutaRegistros);
			cliente = (Cliente) this.persistidor.leerYBuscar(dni);
			this.persistidor.cerrarInput();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
