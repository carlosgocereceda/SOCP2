package modeloBarabasiAlbert;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerarCSV {

	private Red red;
	private int n;
	
	public GenerarCSV(Red red, int n) {
		this.red = red;
		this.n = n;
	}
	
	public void generarFicheros(int it) {
		try {
			//CREAMOS EL CSV DE LOS NODOS
			FileWriter nodos = new FileWriter("nodos" + it + ".csv");
			PrintWriter pw_nodo = new PrintWriter(nodos);
			pw_nodo.println("ID");
			
			//CREAMOS EL CSV DE LAS ARISTAS
			FileWriter aristas = new FileWriter("aristas" + it + ".csv");
			PrintWriter pw_aristas = new PrintWriter(aristas);
			pw_aristas.println("SOURCE	TARGET");
			
			for(int i = 0 ; i < red.getAristas().size(); i ++) {
				pw_aristas.println(red.getAristas().get(i).getNodo1().getValue() + "	" + red.getAristas().get(i).getNodo2().getValue());
			}
			
			for (int i = 1; i <= n; i++) {
				pw_nodo.println(i);
			}
			pw_aristas.close();
			pw_nodo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
