package back.servidor.colaDeEspera;

import java.util.Iterator;

import libreria.clasescompartidas.Cliente;

public class ColaDeEsperaAscendenteStrategy extends ColaDeEspera {

	private static final long serialVersionUID = -7435996972220484293L;

	@Override
	public Cliente obtenerSiguiente() {
		Iterator<Cliente> it = cola.iterator();
		int minDni = 100000000;
		Cliente minDniCliente = null;
		while(it.hasNext()) {
			Cliente auxCliente = it.next();
			int dniCliente = Integer.parseInt(auxCliente.getDni());
			if(dniCliente<minDni) {
				minDni = dniCliente;
				minDniCliente = auxCliente;
			}
		}
		//REVISAR
		this.cola.remove(minDniCliente);
		return minDniCliente;
	}

}
