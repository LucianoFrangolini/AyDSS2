package back.servidor.interfaces;

import libreria.clasescompartidas.Cliente;

public interface AdministracionDeCola {

	public Cliente obtenerProximoCliente();
	public Boolean agregarDni(String dni);
}
