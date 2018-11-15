package modeloAleatorio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GenerarGephi {

	private List<Arista> red;
	
	
	public GenerarGephi(List<Arista> red) {
		this.red = red;
	}
	public void generaFicheros() {
		try {
			//CREAMOS EL CSV DE LOS NODOS
			FileWriter nodos = new FileWriter("nodos.csv");
			@SuppressWarnings("resource")
			PrintWriter pw_nodo = new PrintWriter(nodos);
			pw_nodo.println("ID");
			
			//CREAMOS EL CSV DE LAS ARISTAS
			FileWriter aristas = new FileWriter("aristas.csv");
			@SuppressWarnings("resource")
			PrintWriter pw_aristas = new PrintWriter(aristas);
			pw_aristas.println("SOURCE	TARGET");
			
			for(int i = 0 ; i < red.size(); i ++) {
				pw_aristas.println(red.get(i).getNodo1() + "	" + red.get(i).getNodo2());
			}
			
			for (int i = 1; i <= Main.N; i++) {
				pw_nodo.println(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
