package modeloAleatorio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class GenerarGephi {

	private Set<Arista> red;
	
	
	public GenerarGephi(Set<Arista> red) {
		this.red = red;
	}
	public void generaFicheros() {
		try {
			//CREAMOS EL CSV DE LOS NODOS
			FileWriter nodos = new FileWriter("nodos.csv");
			PrintWriter pw_nodo = new PrintWriter(nodos);
			pw_nodo.println("ID");
			
			//CREAMOS EL CSV DE LAS ARISTAS
			FileWriter aristas = new FileWriter("aristas.csv");
			PrintWriter pw_aristas = new PrintWriter(aristas);
			pw_aristas.println("SOURCE	TARGET");
			
			for(Arista a: red) {
				pw_aristas.println(a.getNodo1() + "	" + a.getNodo2());
			}
			
			for (int i = 1; i <= Main.N; i++) {
				pw_nodo.println(i);
			}
			pw_aristas.close();
			pw_nodo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
