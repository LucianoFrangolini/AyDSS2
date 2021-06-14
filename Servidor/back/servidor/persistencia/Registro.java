package back.servidor.persistencia;

import java.io.Serializable;

/**
 * @author Grupo12. <br>
 *         Clase Registro contiene la informacion de cuando un cliente se
 *         registro, y su DNI, implementa Serializable para su persistencia.
 * 
 */
public class Registro implements Serializable {

	private static final long serialVersionUID = 6287571224468610359L;
	private String dni;
	private String fecha;

	public Registro() {
	}

	public Registro(String dni) {
		this.dni = dni;
		this.fecha = java.util.Calendar.getInstance().getTime().toString();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "[ Dni : " + dni + " || Fecha = " + fecha + " ]";
	}

}
