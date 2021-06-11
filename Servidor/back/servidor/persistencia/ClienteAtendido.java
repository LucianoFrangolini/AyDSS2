package back.servidor.persistencia;

import java.io.Serializable;

public class ClienteAtendido implements Serializable {

	private static final long serialVersionUID = -8164820107088907633L;
	private String nombre;
	private String dni;
	private int numeroPuesto;
	private String fecha;
	
	public ClienteAtendido() {}
	
	public ClienteAtendido(String nombre, String dni, int numeroPuesto) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.numeroPuesto = numeroPuesto;
		this.fecha = java.util.Calendar.getInstance().getTime().toString();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getNumeroPuesto() {
		return numeroPuesto;
	}

	public void setNumeroPuesto(int numeroPuesto) {
		this.numeroPuesto = numeroPuesto;
	}

	@Override
	public String toString() {
		return "[ Nombre : " + nombre + " || DNI : " + dni + " || Numero de Puesto : " + numeroPuesto + " || Fecha de atención : " + fecha + " ]";
	}
	
	
	
	
}
