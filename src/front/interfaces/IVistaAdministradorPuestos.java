package front.interfaces;

import java.util.Iterator;
import front.vistas.VistaPuesto;

public interface IVistaAdministradorPuestos extends IVista{

	static final String ABRIR_PUESTO = "AbrirPuesto";
	static final String ELIMINAR_PUESTO = "EliminarPuesto";
	static final String AGREGAR_PUESTO = "AgregarPuesto";
	
	void actualizarLista(Iterator<IVistaPuesto> it);
	IVistaPuesto getPuestoSeleccionado();
}
