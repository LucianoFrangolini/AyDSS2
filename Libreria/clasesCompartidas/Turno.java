package clasesCompartidas;

import back.servidor.Cliente;

/**
 * @author Grupo12 <br>
 *         Clase para encapsular los atributos de un turno <br>
 */
public class Turno {
	private Integer puesto;
	private Cliente cliente;

	/**
	 * Constructor para el turno<br>
	 * 
	 * @param puesto de tipo Integer: Representa el numero de puesto asignado al
	 *               turno.<br>
	 * @param dni    de tipo String: Representa el dni asignado al turno.<br>
	 */
	public Turno(Integer puesto, Cliente cliente) {
		this.puesto = puesto;
		this.cliente = cliente;
	}

	/**
	 * Método getter del numero de puesto.<br>
	 * 
	 * @return Devuelve el numero de puesto asignado al turno.<br>
	 */
	public Integer getPuesto() {
		return puesto;
	}

	/**
	 * Método getter del dni.<br>
	 * 
	 * @return Devuelve el dni como String.<br>
	 */
	public String getDni() {
		return cliente.getDni();
	}
	
	/**
	 * Método getter del nombre.<br>
	 * 
	 * @return Devuelve el nombre como String.<br>
	 */
	public String getNombre() {
		return cliente.getNombre();
	}
	
	/**
	 * Método getter del cliente.<br>
	 * 
	 * @return Devuelve el cliente como un objeto de ese tipo.<br>
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	
}
