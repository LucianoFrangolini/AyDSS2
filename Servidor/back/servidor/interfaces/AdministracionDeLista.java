package back.servidor.interfaces;

import libreria.clasescompartidas.Cliente;

public interface AdministracionDeLista {

	Boolean agregarTurno(Integer puesto, Cliente cliente);
	void eliminarTurno(Integer puesto);
}
