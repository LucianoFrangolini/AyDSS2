package back.puestos;

import java.util.Iterator;
import java.util.Map.Entry;

import back.direcciones.ListaDeDirecciones;


public class AdministradorDePuestos {
	private ListaDePuestos listaDePuestos = new ListaDePuestos();
	private static int proximoNumeroPuestoDisponible = 1;
	//private static int proximoSocketDisponible;
	//private static int puerto;
	private static AdministradorDePuestos instance;
	
	public static AdministradorDePuestos getInstance() {
		if (instance == null) {
			instance = new AdministradorDePuestos();
		}
		return instance;
	}

	private AdministradorDePuestos() {
		//puerto = ListaDeDirecciones.PUERTO_PUESTOS;
		//proximoSocketDisponible = socketBase + 1;
	}
	
	public Puesto abrirPuestoTrabajo() {
		Puesto puesto = listaDePuestos.agregarPuesto(proximoNumeroPuestoDisponible);
		proximoNumeroPuestoDisponible += 1;
		//proximoSocketDisponible += 1;
		return puesto;
	}
	public void eliminarPuestoTrabajo(int numeroPuesto) {
		//completar
	}
	
	//TAL VEZ NO SE USA
	public Iterator<Entry<Integer,Puesto>> getItPuestos(){
		return listaDePuestos.getItPuestos();
	}
}
 