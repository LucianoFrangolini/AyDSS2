package back.servidor.persistencia;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import libreria.clasescompartidas.Cliente;

@SuppressWarnings("rawtypes")
public class PersistenciaTXT implements IPersistencia{

	private PrintWriter output;
	private BufferedReader input;
	
	@Override
	public void abrirOutput(String nombre) throws IOException {
		File file = new File(nombre);
		if (file.exists()) {
			this.output = new PrintWriter(new BufferedWriter(new FileWriter(nombre,true)));
		}
		else {
			this.output = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
		}
	}

	@Override
	public void cerrarOutput() throws IOException {
		this.output.flush();
		this.output.close();
	}

	@Override
	public void escribir(Object objeto) throws IOException {
		this.output.println(objeto);
	}

	@Override
	public void abrirInput(String nombre) throws IOException {
		this.input = new BufferedReader(new FileReader(nombre));
	}

	@Override
	public void cerrarInput() throws IOException {
		this.input.close();
	}
	
	@Override
	public Object leerYBuscar(String dni) throws IOException, ClassNotFoundException {
		Object obj = null;
		String linea = this.input.readLine();
		int seguir=1;
		String[] datosCliente;
		while (linea!=null && seguir==1) {
			datosCliente = linea.split(",");
			if (datosCliente[1].equalsIgnoreCase(dni)) {
				Cliente cliente = new Cliente(datosCliente[0],datosCliente[1],Integer.parseInt(datosCliente[2]));
				obj = cliente;
				seguir=0;
			}
			linea = this.input.readLine();
		}
		return obj;
	}

}
