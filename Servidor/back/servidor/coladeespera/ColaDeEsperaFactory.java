package back.servidor.coladeespera;

/**
 * @author Grupo 12 <br>
 *         Clase encargada de crear la cola de acuerdo a la configuracion
 *         establecida.<br>
 *         Se implementa el patron Strategy para definir la seleccion del
 *         candidato salir de la cola.
 */
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
