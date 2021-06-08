package back.servidor.persistencia;

import java.io.IOException;

import back.servidor.Cliente;

public class Mapper {
	
	private final String persistirTXT = "TXT";
	
	@SuppressWarnings("rawtypes")
	private IPersistencia persistidorTXT;
	private String tipoDePersistencia;
	
	//Se pueden agregar más persistidores de distintos tipos
	//Asi como distintos tipos de persistencias

	public Mapper(String tipoDePersistencia) {
		this.persistidorTXT = new PersistenciaTXT();
		this.tipoDePersistencia = tipoDePersistencia;
	}
	
	public void persistir(String dni) {
		Registro reg = new Registro(dni);
		if (tipoDePersistencia.equalsIgnoreCase(persistirTXT)); 
				persistirTXT(reg,"Registros.txt");
	}

	public void persistir(Cliente cliente, int numPuesto) {
		ClienteAtendido clienteAtendido = new ClienteAtendido(cliente.getNombre(),cliente.getDni(),numPuesto);
		if (tipoDePersistencia.equalsIgnoreCase(persistirTXT)) 
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
	
	public Cliente buscar(String dni) {
		Cliente cliente = null;
		try {
			this.persistidorTXT.abrirInput("ClientesRegistrados.txt");
			cliente = (Cliente) this.persistidorTXT.leerYBuscar(dni);
			this.persistidorTXT.cerrarInput();	
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return cliente;
	}
}
