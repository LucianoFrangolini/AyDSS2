package back.servidor.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import libreria.clasescompartidas.Cliente;

/**
 * @author Grupo12 <br>
 *         La Clase PersistenciaTXT define los metodos para persistir un archivo
 *         en forma de texto. Implementa IPersistencia para su utilizacion bajo
 *         un patron Mapper<br>
 */
@SuppressWarnings("rawtypes")
public class PersistenciaTXT implements IPersistencia {

	private PrintWriter output;
	private BufferedReader input;

	/**
	 * Metodo encargado de preparar archivo para escritura
	 * 
	 * @param nombre nombre del archivo a escribir
	 */
	@Override
	public void abrirOutput(String nombre) throws IOException {
		File file = new File(nombre);
		if (file.exists()) {
			this.output = new PrintWriter(new BufferedWriter(new FileWriter(nombre, true)));
		} else {
			this.output = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
		}
	}

	/**
	 * Metodo encargado de finalizar el proceso de escritura
	 *
	 */
	@Override
	public void cerrarOutput() throws IOException {
		this.output.flush();
		this.output.close();
	}

	/**
	 * Metodo encargado de escribir en forma de texto el</br>
	 * retorno del metodo toString() propio del objeto.
	 * 
	 * @param objeto objeto a escribir
	 */
	@Override
	public void escribir(Object objeto) throws IOException {
		this.output.println(objeto);
	}

	/**
	 * Metodo encargado de preparar archivo para lectura
	 * 
	 * @param nombre nombre del archivo a leer
	 */
	@Override
	public void abrirInput(String nombre) throws IOException {
		this.input = new BufferedReader(new FileReader(nombre));
	}

	/**
	 * Metodo encargado de finalizar proceso de lectura
	 */
	@Override
	public void cerrarInput() throws IOException {
		this.input.close();
	}

	/**
	 * Metodo encargado de buscar en un archivo de clientes el cliente con cierto
	 * dni<br>
	 * 
	 * @param dni DNI del cliente buscado
	 * @return Referencia clase Object a un cliente
	 */
	@Override
	public Object leerYBuscar(String dni) throws IOException, ClassNotFoundException {
		Object obj = null;
		String linea = this.input.readLine();
		int seguir = 1;
		String[] datosCliente;
		while (linea != null && seguir == 1) {
			datosCliente = linea.split(",");
			if (datosCliente[1].equalsIgnoreCase(dni)) {
				Cliente cliente = new Cliente(datosCliente[0], datosCliente[1], Integer.parseInt(datosCliente[2]));
				obj = cliente;
				seguir = 0;
			}
			linea = this.input.readLine();
		}
		return obj;
	}

}
