package back.servidor.interfaces;

public interface AdministracionDeLista {

	Boolean agregarTurno(Integer puesto, String dni);
	void eliminarTurno(Integer puesto);
}
