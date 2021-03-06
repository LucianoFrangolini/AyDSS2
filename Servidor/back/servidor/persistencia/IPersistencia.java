package back.servidor.persistencia;

import java.io.IOException;

/**
 * @author Grupo12 <br>
 *         Interfaz para la persistencia y asi implementar el patron Mapper.
 *         <br>
 */
public interface IPersistencia<E> {

	void abrirInput(String nombre) throws IOException;

	void abrirOutput(String nombre) throws IOException;

	void cerrarInput() throws IOException;

	void cerrarOutput() throws IOException;

	void escribir(E objeto) throws IOException;

	E leerYBuscar(String dni) throws IOException, ClassNotFoundException;
}
