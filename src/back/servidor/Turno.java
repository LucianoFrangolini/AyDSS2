package back.servidor;

/**
 * @author Grupo12
 * <br>
 * Clase para encapsular los atributos de un turno
 * <br>
 */
public class Turno {
	private Integer puesto;
	private String dni;
	
	/**
	 * Constructor para el turno
	 * @param puesto de tipo Integer: Representa el numero de puesto asignado al turno.<br>
	 * @param dni de tipo String: Representa el dni asignado al turno.<br>
	 */
	public Turno(Integer puesto, String dni) {
		this.puesto = puesto;
		this.dni = dni;
	}
	
	/**
	 * Método getter del Puesto
	 * @return Devuelve el numero de puesto asignado al turno
	 */
	public Integer getPuesto() {
		return puesto;
	}
	
	/**
	 * Método getter del dni
	 * @return Devuelve el dni como String
	 */
	public String getDni() {
		return dni;
	}
}
