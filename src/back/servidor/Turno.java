package back.servidor;

public class Turno {
	private Integer puesto;
	private String dni;
	
	public Turno(Integer puesto, String dni) {
		this.puesto = puesto;
		this.dni = dni;
	}

	public Integer getPuesto() {
		return puesto;
	}
	public String getDni() {
		return dni;
	}
}
