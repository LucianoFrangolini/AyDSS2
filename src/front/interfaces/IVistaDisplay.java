package front.interfaces;

import java.util.Iterator;
import java.util.Map.Entry;


public interface IVistaDisplay extends IVista{
	void actualizarLista(Iterator<Entry<Integer,String>> it);
}
