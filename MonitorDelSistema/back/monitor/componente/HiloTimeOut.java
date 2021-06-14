package back.monitor.componente;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Grupo12<br>
 *         Clase que representa el estado de un componente del sistema.
 *
 */
public class HiloTimeOut implements Runnable {
	private Monitor monitor;
	private String nombre, latido;
	private ScheduledExecutorService scheduler;

	/**
	 * Constructor de HiloTimeOut<br>
	 * 
	 * @param nombre        de tipo String: Representa el nombre del componente
	 *                      conectado.<br>
	 * @param tiempoTimeOut de tipo int: Representa el tiempo antes de considerar
	 *                      desconectado un componente.<br>
	 */
	public HiloTimeOut(String nombre, int tiempoTimeOut) {
		this.nombre = nombre;
		monitor = Monitor.getInstance();
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this, tiempoTimeOut, tiempoTimeOut, TimeUnit.SECONDS);
	}

	public void setLatido(String latido) {
		this.latido = latido;
	}

	@Override
	public void run() {
		Boolean desconectado = false;
		if (this.latido == null)
			desconectado = true;
		if (desconectado) {
			this.monitor.getPcs().firePropertyChange(this.nombre + "Desconectado", null, null);
		}
		this.latido = null;
	}

}
