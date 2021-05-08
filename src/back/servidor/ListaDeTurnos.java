package back.servidor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import back.puestos.Puesto;

@SuppressWarnings("serial")

//REVISAR SERIALIZABLE
public class ListaDeTurnos implements Serializable {
	private HashMap<Integer,String> lista = new HashMap<Integer,String>();
	
	public void agregarTurno(Turno turno) {
		this.lista.put(turno.getPuesto(), turno.getDni());
	}
	public void eliminarTurno(Integer puesto) {
		this.lista.remove(puesto);
	}
	public Boolean isEmpty() {
		return this.lista.isEmpty();
	}
	public int size() {
		return this.lista.size();
	}
	public Iterator<Entry<Integer,String>> getItTurnos(){
		return this.lista.entrySet().iterator();
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.lista.isEmpty())
			sb.append("");
		else {
			Iterator<Entry<Integer, String>> it = this.lista.entrySet().iterator();
			while(it.hasNext()) {
				Entry<Integer, String> entry = it.next();
				sb.append(entry.getKey() + "                               " + entry.getValue()  + "\n");
			}
		}
		return sb.toString();
	}
}
