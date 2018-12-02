package modeloAleatorio;

public class Arista {

	private int nodo1;
	private int nodo2;

	public Arista(int nodo1, int nodo2) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
	}

	public int getNodo1() {
		return nodo1;
	}

	public void setNodo1(int nodo1) {
		this.nodo1 = nodo1;
	}

	public int getNodo2() {
		return nodo2;
	}

	public void setNodo2(int nodo2) {
		this.nodo2 = nodo2;
	}

	@Override
	public boolean equals(Object o) {
		return o != null &&
				o instanceof Arista &&
				( ((Arista) o).nodo1 == this.nodo1 && ((Arista) o).nodo2 == this.nodo2 ||
				((Arista) o).nodo1 == this.nodo2 && ((Arista) o).nodo2 == this.nodo1);
	}
	
	@Override
	public String toString() {
		return this.nodo1 + " " + this.nodo2;
	}
	
	@Override
	public int hashCode() {
		String s = Integer.toString(this.nodo1) + Integer.toString(this.nodo2);
		return Integer.parseInt(s); 
	}
}
