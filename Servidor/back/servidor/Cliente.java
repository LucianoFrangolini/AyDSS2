package back.servidor;

public class Cliente {
	private String dni, nombre;
	private Integer prioridad;
	
	public Cliente(String dni) {
		this.dni = dni;
		this.nombre = null;
		this.prioridad = 0;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPrioridad() {
		return prioridad;
	}
}
