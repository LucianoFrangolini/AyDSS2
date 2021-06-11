package libreria.clasescompartidas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import back.servidor.componente.Cliente;

/**
 * @author Grupo12 <br>
 *         Clase ListaDeTurnos que representa una lista de turnos a mostrar en
 *         el display y atender por los Puestos de trabajo. Implementa
 *         Serializable para permitir ser enviado por socket<br>
 */
public class ListaDeTurnos implements Serializable {
	private static final long serialVersionUID = -6925984720747589926L;
	private HashMap<Integer, Cliente> lista = new HashMap<Integer, Cliente>();

	/**
	 * M�todo encargado de agregar un turno a la lista de turnos.<br>
	 * 
	 * <b> Pre: </b> El turno debe ser distinto de null.<br>
	 * <b> Post: </b> Se agrega un turno a la lista de turnos<br>
	 * 
	 * @param turno de tipo Turno: Representa el turno a ser agregado en la
	 *              lista.<br>
	 */
	public void agregarTurno(Turno turno) {
		this.lista.put(turno.getPuesto(), turno.getCliente());
	}

	/**
	 * M�todo encargado de eliminar un turno de la lista de turnos.<br>
	 * 
	 * <b> Post: </b> Se elimina un turno de la lista de turnos.<br>
	 * 
	 * @param puesto de tipo Integer: Representa el puesto del turno a eliminar.<br>
	 */
	public void eliminarTurno(Integer puesto) {
		this.lista.remove(puesto);
	}

	/**
	 * M�todo encargado de comprobar si la listaDeTurnos se encuentra vac�a.<br>
	 * 
	 * @return true si la lista se encuentra vac�a, false si la lista no se
	 *         encuentra vac�a.<br>
	 */
	public Boolean isEmpty() {
		return this.lista.isEmpty();
	}

	/**
	 * M�todo encargado de devolver la cantidad de turnos que se encuentran en la
	 * lista de turnos<br>
	 * 
	 * @return la cantidad de turnos en la lista.<br>
	 */
	public int size() {
		return this.lista.size();
	}

	/**
	 * M�todo encargado de devolver un iterador sobre los turnos de la lista.<br>
	 * 
	 * @return iterador sobre la lista de turnos.<br>
	 */
	public Iterator<Entry<Integer, Cliente>> getIteratorTurnos() {
		return this.lista.entrySet().iterator();
	}

	/**
	 * M�todo toString de la clase ListaDeTurnos.<br>
	 * 
	 * @return Devuelve el string que representa el objeto.<br>
	 */
	@Override
	public String toString() {
		String auxIdentificador;
		StringBuilder sb = new StringBuilder();
		if (this.lista.isEmpty())
			sb.append("");
		else {
			Iterator<Entry<Integer, Cliente>> it = this.lista.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, Cliente> entry = it.next();
				Cliente cliente = entry.getValue();
				auxIdentificador = cliente.getNombre();
				if(auxIdentificador==null)
					auxIdentificador = cliente.getDni(); 
				sb.append(entry.getKey() + "                               " + auxIdentificador + "\n");
			}
		}
		return sb.toString();
	}
}
