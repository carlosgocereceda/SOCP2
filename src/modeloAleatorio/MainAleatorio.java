package modeloAleatorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;

public class MainAleatorio {

	public static long N;
	public static double p;
	public static int numIteraciones = 10;
	
	private static JFrame frame;
	private static AleatorioPanel panel;
	private static Set<Arista> paresOriginal;
	
	public static void main(String[] args) {	
		if(args.length < 2) {
			System.out.println("USO: program <N> <p> <numIteraciones>");
			System.out.println("Iniciando modo GUI por defecto...");
			
			frame = new JFrame();
			frame.setTitle("Analisis de Redes Sociales");
			frame.setSize(1000, 650);
			panel = new AleatorioPanel();
			frame.setContentPane(panel);
			frame.setVisible(true);
		} else {
			N = Long.parseLong(args[0]);
			p = Double.parseDouble(args[1]);
			
			if(args.length > 2)
				MainAleatorio.numIteraciones = Integer.parseInt(args[2]);
			
			System.out.println("N: " + N + "  p: " + p);
			
			MainAleatorio.comenzar();
			
			// Exportar un ejemplo
			// new GenerarGephi(generarSetAristas()).generaFicheros();
		}
	}
	// Metodo que sera llamado en la vista(GUI)
	public static void comenzar() {
		simular(numIteraciones);
	}
	// Metodo resposable de realizar la logica de la aplicacion
	public static void simular(int iteraciones) {
		// En este arrayList nos hiremos guardando los datos de cada iteracion, 
		// para mas adelante hacer la media total de todas las iteraciones
		List<Estadisticas> estadisticas = new ArrayList<>();

		System.out.println("Comenzando la simulacion de una red aleatoria de N = " + N + " y p =" + p);

		// Esta metodo lo que hara es crear la red una sola vez y mas adelante 
		// eligiremos que aristas cogeremos, todo de forma aleatoria
		inicializarRed();
		// Iremos creando varias redes aleatorias, segun el numero de iteraciones
		for (int i = 0; i < iteraciones; i++) {
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(generarSetAristas(i));

			// Datos que nos dara el conversor, como la densidad de la red, coeficiente de clustering, etc
			GraphDensity gd = conversor.getDensity();
			GraphDistance gdis = conversor.getGraphDistance();
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();

			// Guardamos toda esta informacion en un transfer
			Estadisticas e = new Estadisticas(conversor.getNumAristas(), gd.getDensity(), conversor.getShortestHubDegree(), conversor.getLargestHubDegree(),gdis.getPathLength(),
					cc.getAverageClusteringCoefficient(),
					conversor.getDegree().getAverageDegree(),
					conversor.getConnectedComponents().getConnectedComponentsCount());
			// Y a su vez lo guardamos en la lista de estadisticas
			estadisticas.add(e);
			
			// Mostramos la informacion de los datos por el JLabel de la izquierda de la inferfaz de la aplicación
			String info = System.getProperty("line.separator") + "Simulacion numero: " + (i + 1) + " terminada";
			panel.escribe(info);
			frame.repaint();
			info = "Num. Aristas: " + e.getNumAristas();
			panel.escribe(info);
			info = "Density: " + e.getDensity();
			panel.escribe(info);
			info = "Largest Hub Degree: " + e.getLargestHubDegree();
			panel.escribe(info);
			info = "Shortest Hub Degree: " + e.getShortestHubDegree();
			panel.escribe(info);
			info = "Avg. Distance: " + e.getAvgDistance();
			panel.escribe(info);
			info = "Average clustering coefficient: " + e.getAvgClustCoefficient();
			panel.escribe(info);
			info = "Degree: " + e.getAvgDegree();
			panel.escribe(info);
			info = "Connected components: " + e.getConnectedComponents();
			panel.escribe(info);
			frame.repaint();
			frame.update(frame.getGraphics());
			System.out.println(System.getProperty("line.separator") + "Simulacion numero: " + (i + 1) + " terminada:");
			System.out.println("Num. Aristas: " + e.getNumAristas());
			System.out.println("Density: " + e.getDensity());
			System.out.println("Largest Hub Degree: " + e.getLargestHubDegree());
			System.out.println("Shortest Hub Degree: " + e.getShortestHubDegree());
			System.out.println("Avg. Distance: " + e.getAvgDistance());			
			System.out.println("Average clustering coefficient: " + e.getAvgClustCoefficient());
			System.out.println("Degree: " + e.getAvgDegree());
			System.out.println("Connected components: " + e.getConnectedComponents());
		}
		// Después de a ver hecho las correspondientes iteraciones
		// Declaro las variables que calcularan 
		double sNumAristas = 0, avgNumAristas = 0, sDensity = 0, avgDensity = 0, sAvgDistance = 0, avgDistance = 0, sAvgClustCoefficient = 0, avgClustCoefficient = 0, sLargestHubDegree = 0, avgLargestHubDegree = 0, sShortestHubDegree = 0, avgShortestHubDegree = 0,
				sAvgDegree = 0, avgDegree = 0, sConnectedComponents = 0, avgConnectedComponents = 0;
		
		for (Estadisticas e : estadisticas) {
			sNumAristas += e.getNumAristas();
			sDensity += e.getDensity();
			sLargestHubDegree += e.getLargestHubDegree();
			sAvgDistance += e.getAvgDistance();
			sAvgClustCoefficient += e.getAvgClustCoefficient();
			sAvgDegree += e.getAvgDegree();
			sConnectedComponents += e.getConnectedComponents();
			sShortestHubDegree += e.getShortestHubDegree();
		}
		
		avgNumAristas = sNumAristas / iteraciones;
		avgDensity = sDensity / iteraciones;
		avgLargestHubDegree = sLargestHubDegree / iteraciones;
		avgDistance = sAvgDistance / iteraciones;
		avgClustCoefficient = sAvgClustCoefficient / iteraciones;
		avgDegree = sAvgDegree / iteraciones;
		avgConnectedComponents = sConnectedComponents / iteraciones;
		avgShortestHubDegree = sShortestHubDegree / iteraciones;
		
		String info = "######## RESULTADO FINAL ##########";
		panel.escribe(info);
		info = "Num. Aristas: " + avgNumAristas;
		panel.escribe(info);
		info = "Density: " + avgDensity;
		panel.escribe(info);
		info = "Largest Hub Degree: " + avgLargestHubDegree;
		panel.escribe(info);
		info = "Shortest Hub Degree: " + avgShortestHubDegree;
		panel.escribe(info);
		info = "Avg. Distance: " + avgDistance;
		panel.escribe(info);
		info = "Average clustering coefficient: " + avgClustCoefficient;
		panel.escribe(info);
		info = "Degree: " + avgDegree;
		panel.escribe(info);
		info = "Connected components: " + avgConnectedComponents;
		panel.escribe(info);
		frame.repaint();
		System.out.println("######## RESULTADO FINAL ##########");
		System.out.println("Num. Aristas: " + avgNumAristas);
		System.out.println("Density: " + avgDensity);
		System.out.println("Largest Hub Degree: " + avgLargestHubDegree);
		System.out.println("Shortest Hub Degree: " + avgShortestHubDegree);
		System.out.println("Avg. Distance: " + avgDistance);			
		System.out.println("Average clustering coefficient: " + avgClustCoefficient);
		System.out.println("Degree: " + avgDegree);
		System.out.println("Connected components: " + avgConnectedComponents);
	}

	// Creamos todas las posibles aristas
	public static void inicializarRed(){
		Set<Arista> lista = new HashSet<Arista>();
		// Creamos todas las aristas posibles
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				// Creamos una arista que va de "i" a "j"
				Arista a = new Arista(i, j);
				// Creamos una arista que va de "j" a "i"
				Arista a2 = new Arista(j, i);
				// Nos aseguramos de no coger aristas repetidas y lo guardamos
				// en el HashMap de "pares"
				if (i != j && !lista.contains(a) && !lista.contains(a2))
				//if(!lista.contains(a))
					lista.add(a);
			}
		}
		System.out.println("Tamaño de la red despues de haber hecho todas las aristas: "+ lista.size());
		paresOriginal = lista;
	}
	// Metodo que genera las aristas
	public static Set<Arista> generarSetAristas(int it) {
		Set<Arista> pares = paresOriginal;
		Set<Arista> aristas = new HashSet<Arista>();
		
		// Creamos todas las aristas posibles
		/*for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				// Creamos una arista que va de "i" a "j"
				Arista a = new Arista(i, j);
				// Creamos una arista que va de "j" a "i"
				Arista a2 = new Arista(j, i);
				// Nos aseguramos de no coger aristas repetidas y lo guardamos en el HashMap de "pares"
				if(i != j && !pares.contains(a) && !pares.contains(a2))
					pares.add(a);
			}
		}*/
		
		if(pares.size() != ((N * (N - 1) ) / 2))
			System.out.println("Numero de pares: " + pares.size() + " debian ser: " + ((N * (N - 1) ) / 2));
		// Nos recorremos el HashMap de "pares"
		for (Arista a : pares) {
			// Eligiendo aleatoriamente con que aristas nos quedamos y
			// con las que nos quedemos las guardamos en el Hashmap "aristas"
			if(generarRandom(p))
				aristas.add(a);
		}
		// Genera los CSVs de los nodos y de las aristas
		new GenerarGephi(aristas).generaFicheros(it);
		
		return aristas;
	}

	// Metodo que da aleatoriamente un boolean, usando la probabilidad "p" que el usuario eligio
	public static boolean generarRandom(double p){
		Double result;
		Random r= new Random();
		result = r.nextDouble();
		
		return result <= p;
	}
}
