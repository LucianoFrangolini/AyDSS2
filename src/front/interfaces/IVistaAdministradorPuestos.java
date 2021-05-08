package front.interfaces;

import java.awt.event.ActionListener;
import java.util.Iterator;

public interface IVistaAdministradorPuestos extends IVista{

	static final String ABRIR_PUESTO = "AbrirPuesto";
	static final String ELIMINAR_PUESTO = "EliminarPuesto";
	static final String AGREGAR_PUESTO = "AgregarPuesto";
	
	void setActionListener(ActionListener c);
	void actualizarLista(Iterator<IVistaPuesto> it);
	IVistaPuesto getPuestoSeleccionado();
}
