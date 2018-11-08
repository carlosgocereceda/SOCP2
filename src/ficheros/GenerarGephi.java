package ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import arista.Arista;

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
			pw_nodo.println("ID");
			pw_aristas.println("SOURCE	TARGET");
			for(int i = 0 ; i < red.size(); i ++) {
				pw_nodo.print(red.get(i).getNodo1());
				pw_nodo.print(red.get(i).getNodo2());
				pw_aristas.print(red.get(i).getNodo1() + "	" + red.get(i).getNodo2());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
