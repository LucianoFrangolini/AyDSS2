package front.interfaces;

public interface IVistaServidor extends IVista{

	static final String ABRIR_PUESTO = "AbrirPuesto";
	static final String ELIMINAR_PUESTO = "EliminarPuesto";
	static final String AGREGAR_PUESTO = "AgregarPuesto";
	
	void setActionCommands();
}
