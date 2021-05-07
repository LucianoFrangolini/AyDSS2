package front.interfaces;

import java.util.ArrayList;

import back.servidor.Turno;

public interface IVistaDisplay extends IVista{
	void actualizarLista(ArrayList<Turno> listaDeTurnos);
}
