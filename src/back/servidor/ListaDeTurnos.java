package back.servidor;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaDeTurnos {
	//private ArrayList<Turno> lista = new ArrayList<Turno>();
	private HashMap<Integer,String> lista = new HashMap<Integer,String>();
	
	public void añadirTurno(Turno turno) {
		this.lista.put(turno.getPuesto(), turno.getDni());
	}
	public void eliminarTurno(Integer puesto) {
		this.lista.remove(puesto); 
	}
	
}
