package back.puestos;

import java.util.HashMap;

import back.servidor.AdministradorDeTurnos;

public class ListaDePuestos {
	private HashMap<Integer,Puesto> puestos = new HashMap<Integer,Puesto>();
	
	public void agregarPuesto(int numeroPuesto, int puerto) {
		Puesto puesto = new Puesto(numeroPuesto, puerto);
		this.puestos.put(numeroPuesto, puesto);
	}
	public void eliminarPuesto(int numeroPuesto) {
		this.puestos.remove(numeroPuesto); 
	}
}
