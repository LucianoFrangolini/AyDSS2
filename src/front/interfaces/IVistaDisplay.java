package front.interfaces;

import java.util.ArrayList;

import back.servidor.Turno;

public interface IVistaDisplay extends IVista{
	//revisar
	void actualizarLista(ArrayList<Turno> listaDeTurnos);
}
