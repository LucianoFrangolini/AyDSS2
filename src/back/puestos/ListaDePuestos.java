package back.puestos;

import java.util.HashMap;

public class ListaDePuestos {
	private HashMap<Integer,Puesto> puestos = new HashMap<Integer,Puesto>();
	
	public Puesto agregarPuesto(int numeroPuesto) {
		Puesto puesto = new Puesto(numeroPuesto);
		this.puestos.put(numeroPuesto, puesto);
		return puesto;
	}
	public void eliminarPuesto(int numeroPuesto) {
		this.puestos.remove(numeroPuesto); 
	}
	public Puesto getPuesto(int numeroPuesto) {
		return this.puestos.get(numeroPuesto);
	}
}
