package back.servidor.persistencia;

import java.io.IOException;

import back.servidor.Cliente;

public class Mapper {
	
	@SuppressWarnings("rawtypes")
	private IPersistencia persistidorTXT;

	public Mapper() {
		this.persistidorTXT = new PersistenciaTXT(); 
	}
	
	public void persistir(String dni, String tipo) {
		Registro reg = new Registro(dni);
		if (tipo.equalsIgnoreCase("TXT")) 
				persistirTXT(reg,"Registros.txt");
	}

	public void persistir(Cliente cliente, int numPuesto, String tipo) {
		ClienteAtendido clienteAtendido = new ClienteAtendido(cliente.getNombre(),cliente.getDni(),numPuesto);
		if (tipo.equalsIgnoreCase("TXT")) 
				persistirTXT(clienteAtendido,"ClientesAtendidos.txt");
	}
	
	@SuppressWarnings("unchecked")
	private void persistirTXT(Object obj, String archivo) {
		try {
			this.persistidorTXT.abrirOutput(archivo);
			this.persistidorTXT.escribir(obj);
			this.persistidorTXT.cerrarOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
