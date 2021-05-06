package back.servidor;

import java.util.Arrays;
import java.util.List;

public class ListaDePuertos {

	protected static final Puerto PUERTO_TOTEM = new Puerto(9000,"PUERTO_TOTEM");
	protected static final Puerto PUERTO_OPERADOR = new Puerto(8000,"PUERTO_OPERADOR");
	protected static final Puerto PUERTO_DISPLAY = new Puerto(7000,"PUERTO_DISPLAY");

	protected static List<Puerto> puertos = Arrays.asList(PUERTO_TOTEM, PUERTO_OPERADOR, PUERTO_DISPLAY);
	
	protected Puerto buscarPuerto(String nombre) {
		int i=0;
		Puerto ret=null;
		while (i<puertos.size() && nombre != puertos.get(i).getNombre()) 
			i++;
		if (i<puertos.size())
			ret = puertos.get(i);
		return ret;
	}
	
}
