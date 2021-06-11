package back.servidor.coladeespera;

import java.util.Iterator;

import libreria.clasescompartidas.Cliente;

public class ColaDeEsperaDescendenteStrategy extends ColaDeEspera {

	private static final long serialVersionUID = 7793532571270304044L;

	@Override
	public Cliente obtenerSiguiente() {
		Iterator<Cliente> it = cola.iterator();
		int maxDni = -1;
		Cliente maxDniCliente = null;
		while(it.hasNext()) {
			Cliente auxCliente = it.next();
			int dniCliente = Integer.parseInt(auxCliente.getDni());
			if(dniCliente>maxDni) {
				maxDni = dniCliente;
				maxDniCliente = auxCliente;
			}
		}
		//REVISAR
		this.cola.remove(maxDniCliente);
		return maxDniCliente;
	}

}
