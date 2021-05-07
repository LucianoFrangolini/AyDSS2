package back.puestos;

import java.beans.PropertyChangeSupport;

import back.servidor.AdministradorDeTurnos;

public class AdministradorDePuestos {
	private ListaDePuestos listaDePuestos = new ListaDePuestos();
	private static int proximoNumeroPuestoDisponible = 1;
	private static int proximoSocketDisponible;
	private static int socketBase;
	private static AdministradorDePuestos instance;
	
	public static AdministradorDePuestos getInstance() {
		if (instance == null) {
			instance = new AdministradorDePuestos();
		}
		return instance;
	}

	private AdministradorDePuestos() {
		socketBase = 8000;
		proximoSocketDisponible = socketBase;
	}
	
	public void abrirPuestoTrabajo() {
		listaDePuestos.agregarPuesto(proximoNumeroPuestoDisponible, 8001);
		//proximoNumeroPuertoDisponible += 1;
		proximoNumeroPuestoDisponible += 1;
	}
	public void eliminarPuestoTrabajo(int numeroPuesto) {
		//completar
	}
}
