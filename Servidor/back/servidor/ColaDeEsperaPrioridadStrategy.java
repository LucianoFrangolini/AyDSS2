package back.servidor;

import java.util.Iterator;

public class ColaDeEsperaPrioridadStrategy extends ColaDeEspera {

	private static final long serialVersionUID = -1777719770132422628L;

	@Override
	public Cliente obtenerSiguiente() {
		Iterator<Cliente> it = cola.iterator();
		int maxPrio = -1;
		Cliente maxPrioCliente = null;
		while(it.hasNext()) {
			Cliente auxCliente = it.next();
			if(auxCliente.getPrioridad()>maxPrio) {
				maxPrio = auxCliente.getPrioridad();
				maxPrioCliente = auxCliente;
			}
		}
		//REVISAR
		this.cola.remove(maxPrioCliente);
		return maxPrioCliente;
	}

}
