package back.puestos;

public class AdministradorDePuestos {
	private ListaDePuestos listaDePuestos = new ListaDePuestos();
	private static int proximoNumeroPuestoDisponible = 1;
	private static AdministradorDePuestos instance;
	
	public static AdministradorDePuestos getInstance() {
		if (instance == null) {
			instance = new AdministradorDePuestos();
		}
		return instance;
	}

	private AdministradorDePuestos() {}
	
	public Puesto abrirPuestoTrabajo() {
		Puesto puesto = listaDePuestos.agregarPuesto(proximoNumeroPuestoDisponible);
		proximoNumeroPuestoDisponible += 1;
		return puesto;
	}
	public void eliminarPuestoTrabajo(int numeroPuesto) {
		this.listaDePuestos.eliminarPuesto(numeroPuesto);
	}
	public Puesto getPuesto(int numeroPuesto) {
		return this.listaDePuestos.getPuesto(numeroPuesto);
	}
}
 