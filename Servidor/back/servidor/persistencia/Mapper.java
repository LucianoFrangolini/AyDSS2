package back.servidor.persistencia;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import libreria.clasescompartidas.Cliente;

public class Mapper {
	
	private final String persistirTXT = "TXT";
	
	@SuppressWarnings("rawtypes")
	private IPersistencia persistidor;
	private String tipoDePersistencia;
	private String rutaIngresos;
	private String rutaAtendidos;
	private String rutaRegistros;
	
	//Se pueden agregar más persistidores de distintos tipos
	//Asi como distintos tipos de persistencias

	public Mapper() {
		cargarPropiedades();
		definirPersistencia();
	}
	
	private void cargarPropiedades() {
		try {
			Properties p = new Properties();
			p.load(new FileReader("Libreria/propiedades/persistencia.properties"));
			this.tipoDePersistencia=p.getProperty("TIPO_DE_PERSISTENCIA");
			this.rutaIngresos=p.getProperty("RUTA_DE_PERSISTENCIA_INGRESOS");
			this.rutaAtendidos=p.getProperty("RUTA_DE_PERSISTENCIA_ATENDIDOS");
			this.rutaRegistros=p.getProperty("RUTA_DE_REGISTRO_DE_CLIENTES");
		} catch (FileNotFoundException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "No se encontró el archivo de configuración de persistencia");
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Se encontró un problema leyendo el archivo de configuración de persistencia");
		}
	}
	
	private void definirPersistencia() {
		switch(this.tipoDePersistencia) {
			case persistirTXT: this.persistidor = new PersistenciaTXT();
			break;			
		}
	}
	
	public void persistir(String dni) {
		Registro reg = new Registro(dni);
		if (tipoDePersistencia.equalsIgnoreCase(persistirTXT)); 
				persistirTXT(reg,this.rutaIngresos);
	}

	public void persistir(Cliente cliente, int numPuesto) {
		String nombre = cliente.getNombre();
		if (nombre==null)
			nombre="No registrado";
		ClienteAtendido clienteAtendido = new ClienteAtendido(nombre,cliente.getDni(),numPuesto);
		if (tipoDePersistencia.equalsIgnoreCase(persistirTXT)) 
				persistirTXT(clienteAtendido,this.rutaAtendidos);
	}
	
	@SuppressWarnings("unchecked")
	private void persistirTXT(Object obj, String archivo) {
		try {
			this.persistidor.abrirOutput(archivo);
			this.persistidor.escribir(obj);
			this.persistidor.cerrarOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
