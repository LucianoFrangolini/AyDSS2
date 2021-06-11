package back.servidor.interfaces;

import back.servidor.componente.Cliente;

public interface AdministracionDeLista {

	Boolean agregarTurno(Integer puesto, Cliente cliente);
	void eliminarTurno(Integer puesto);
}
