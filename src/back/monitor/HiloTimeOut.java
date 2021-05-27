package back.monitor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HiloTimeOut implements Runnable{
	private Monitor monitor;
	private String nombre, latido;
	private ScheduledExecutorService scheduler;
	private int initialDelay;
	private int periodicDelay;
	


	public HiloTimeOut(String nombre) {
		this.nombre = nombre;
		monitor = Monitor.getInstance();
		scheduler = Executors.newSingleThreadScheduledExecutor();
		this.initialDelay = 15000;
		this.periodicDelay = 15000;
		scheduler.scheduleAtFixedRate(this, initialDelay, periodicDelay, TimeUnit.MILLISECONDS);
	}

	public void setLatido(String latido) {
		this.latido = latido;
	}
	
	@Override
	public void run() {
		Boolean desconectado = false;
		if(this.latido==null)
			desconectado = true;
		if(desconectado) {
			this.monitor.getPcs().firePropertyChange(this.nombre + "Desconectado", null, null);
		}
		this.latido = null;
	}
	
}
