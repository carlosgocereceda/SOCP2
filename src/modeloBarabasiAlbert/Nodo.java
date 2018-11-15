package modeloBarabasiAlbert;

public class Nodo {
	private int value;
	private int degree;
	
	public Nodo(int i, int g) {
		this.value = i;
		this.degree = g;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void incrementDegree() {
		this.degree += 1;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null &&
				o instanceof Nodo && ((Nodo) o).value == this.value && ((Nodo) o).degree == this.degree;
	}
	
	@Override
	public String toString() {
		return this.value + " " + this.degree;
	}
}
