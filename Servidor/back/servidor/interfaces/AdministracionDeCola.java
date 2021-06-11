package back.servidor.interfaces;

import back.servidor.componente.Cliente;

public interface AdministracionDeCola {

	public Cliente obtenerProximoCliente();
	public Boolean agregarDni(String dni);
}
