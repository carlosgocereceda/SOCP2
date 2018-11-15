package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.List;

public class Red {
	List<Arista> aristas;
	
	public List<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista> aristas) {
		this.aristas = aristas;
	}

	public Red() {
		this.aristas = new ArrayList<Arista>();
	}
	
	public Red(List<Arista> aristas) {
		this.aristas = aristas;
	}
	
	public void pushArista(Arista arista) {
		this.aristas.add(arista);
	}
	
}
