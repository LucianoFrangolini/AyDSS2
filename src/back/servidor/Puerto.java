package back.servidor;

public class Puerto {

	private int puerto;
	private String nombre;
	
	public Puerto(int puerto,String nombre) {
		this.puerto=puerto;
		this.nombre=nombre;
	}
	
	public int getPuerto() {
		return puerto;
	}
	
	public String getNombre() {
		return nombre;
	}

}
