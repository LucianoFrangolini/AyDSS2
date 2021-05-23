package front.interfaces;

public interface IVistaServidor extends IVista{

	static final String ABRIR_SERVIDOR = "AbrirServidor1";
	static final String ABRIR_S2 = "AbrirServidor2";
	static final String CERRAR_SERVIDOR = "CerrarServidor1";
	static final String CERRAR_S2 = "CerrarServidor2";
	
	void setActionCommands();
	void cambiarEstado();
}
