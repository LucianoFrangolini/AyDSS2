package back.servidor.persistencia;

import java.io.IOException;

public interface IPersistencia <E>{

	void abrirOutput(String nombre) throws IOException;
	void cerrarOutput() throws IOException;
	void escribir(E objeto) throws IOException;
}
