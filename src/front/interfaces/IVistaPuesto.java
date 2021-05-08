package front.interfaces;

public interface IVistaPuesto extends IVista {

	static final String LLAMAR = "Llamar";
	static final String ELIMINAR = "Eliminar";
	
	void setDisplay(String clienteActual);
	int getNumeroPuesto();
	void dispose();
}
