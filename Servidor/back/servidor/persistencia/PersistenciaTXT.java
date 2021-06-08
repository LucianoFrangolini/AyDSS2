package back.servidor.persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("rawtypes")
public class PersistenciaTXT implements IPersistencia{

	private PrintWriter output;
	
	@Override
	public void abrirOutput(String nombre) throws IOException {
		File file = new File(nombre);
		if (file.exists()) {
			this.output = new PrintWriter(new BufferedWriter(new FileWriter(nombre,true)));
			System.out.println("existe");
		}
		else {
			System.out.println("no existe");
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

}
