package back.puestos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
	
	
	//TAL VEZ NO SE USA
	public Iterator<Entry<Integer,Puesto>> getItPuestos(){
		return puestos.entrySet().iterator();
	}
}
