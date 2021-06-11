package back.servidor.componente;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 5183470916035129940L;
	
	private String dni, nombre;
	private Integer prioridad;
	
	public Cliente(String dni) {
		this.dni = dni;
		this.nombre = null;
		this.prioridad = 0;
	}
	
	public Cliente(String nombre,String dni, Integer prioridad) {
		this.dni = dni;
		this.nombre = nombre;
		this.prioridad = prioridad;
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
