package back.servidor.colaDeEspera;

public class ColaDeEsperaFactory {
	
	public static ColaDeEspera crearColaDeEspera(String tipo) {
		ColaDeEspera colaDeEspera = null;
		switch (tipo) {
			case "FIFO": {
				colaDeEspera = new ColaDeEsperaFIFOStrategy();
				break;
			}
			case "PRIORIDAD": {
				colaDeEspera = new ColaDeEsperaPrioridadStrategy();
				break;
			}
			case "ASCENDENTE": {
				colaDeEspera = new ColaDeEsperaAscendenteStrategy();
				break;
			}
			case "DESCENDENTE": {
				colaDeEspera = new ColaDeEsperaDescendenteStrategy();
				break;
			}
		}
		return colaDeEspera;
	}
}
