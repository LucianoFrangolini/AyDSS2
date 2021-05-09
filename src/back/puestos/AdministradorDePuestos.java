package back.puestos;

/**
 * @author Grupo12
 * <br>
 * Clase del Administrador de Puestos que implementa el patrón Singleton.
 * <br>
 */
public class AdministradorDePuestos {
	private ListaDePuestos listaDePuestos = new ListaDePuestos();
	private static int proximoNumeroPuestoDisponible = 1;
	private static AdministradorDePuestos instance;
	
	/**
	 * Método encargado de obtener la instancia de la clase AdministradorDePuestos.<br>
	 * @return una instancia de AdministradorDePuestos.
	 */
	public static AdministradorDePuestos getInstance() {
		if (instance == null) {
			instance = new AdministradorDePuestos();
		}
		return instance;
	}
	
	/**
	 * Constructor para el administrador de puestos.<br>
	 */
	private AdministradorDePuestos() {}
	
	/**
	 * Método encargado de abrir un puesto y devolver la referencia al mismo.<br>
	 * @return la instancia de Puesto creado.
	 */
	public Puesto abrirPuestoTrabajo() {
		Puesto puesto = listaDePuestos.agregarPuesto(proximoNumeroPuestoDisponible);
		proximoNumeroPuestoDisponible += 1;
		return puesto;
	}
	
	/**
	 * Método encargado de eliminar el puesto solicitado a traves del número que lo identifica.<br>
	 * <b> Post: </b> Se elimina el puesto solicitado si es que existia.<br>
	 */
	public void eliminarPuestoTrabajo(int numeroPuesto) {
		this.listaDePuestos.eliminarPuesto(numeroPuesto);
	}
	
	/**
	 * Método encargado de devolver un puesto pedido.<br>
	 * @return la instancia de Puesto pedida o null si no existe.
	 */
	public Puesto getPuesto(int numeroPuesto) {
		return this.listaDePuestos.getPuesto(numeroPuesto);
	}
}
 