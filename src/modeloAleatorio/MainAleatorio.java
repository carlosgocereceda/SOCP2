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

	public static int N;
	public static double p;
	public static int numIteraciones = 10;
	
	private static JFrame frame;
	private static AleatorioPanel panel;
	
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
			N = Integer.parseInt(args[0]);
			p = Double.parseDouble(args[1]);
			
			if(args.length > 2)
				MainAleatorio.numIteraciones = Integer.parseInt(args[2]);
			
			System.out.println("N: " + N + "  p: " + p);
			
			MainAleatorio.comenzar();
		}
	}
	/**
	 * Comienza la simulación
	 * sera llamado en la vista(GUI)
	 */
	public static void comenzar() {
		simular(numIteraciones);
	} 
	/**
	 * Metodo resposable de realizar la logica de la aplicacion
	 * @param iteraciones numero de veces que se va a ejecutar el programa
	 */
	public static void simular(int iteraciones) {
		List<Estadisticas> estadisticas = new ArrayList<>();//ArrayList en el que guardamos los datos de cada iteración
		// para mas adelante hacer la media total de todas las iteraciones
		System.out.println("Comenzando la simulacion de una red aleatoria de N = " + N + " y p =" + p);
		// Iremos creando varias redes aleatorias, segun el numero de iteraciones
		for (int i = 0; i < iteraciones; i++) {
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(generarSetAristas(i),N);

			// Datos que nos dara el conversor, como la densidad de la red, coeficiente de clustering, etc
			GraphDensity gd = conversor.getDensity();
			GraphDistance gdis = conversor.getGraphDistance();
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();

			// Guardamos la informacion de las estadisticas
			Estadisticas e = new Estadisticas(conversor.getNumAristas(), gd.getDensity(), conversor.getShortestHubDegree(), conversor.getLargestHubDegree(),gdis.getPathLength(),
					cc.getAverageClusteringCoefficient(),
					conversor.getDegree().getAverageDegree(),
					conversor.getConnectedComponents().getConnectedComponentsCount());
			estadisticas.add(e);
			
			/*Set<Arista> aristas = generarSetAristas(i);
			*/
			/*Set<Arista> aristas = generarSetAristas(i);
			// Hacemos los calculos correspondientes

			Calculos calculos = new Calculos(aristas,N);
			// Los guardamos en la lisata de "estadisticas" para posteriormente hacer la media
			Estadisticas e = new Estadisticas(calculos.getNumAristas(), calculos.getDensity(), calculos.getMinHub(), calculos.getMaxHub(), calculos.getAvarageDistance(),
					calculos.getClustering(),
					calculos.getGradoMedio(),
					0);
			estadisticas.add(e);*/
			// Mostramos la informacion
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

	/**
	 * Método utilizado para crear inicialmente la red
	 * Unicamente es llamado una vez al comenzar la ejecucion
	 * Genera todas las aristas posibles.
	 * En cada iteración se cogen de manera aleatoria aristas de aquí
	 */
	
	/**
	 * Metodo que genera las aristas
	 * De entre todas las aristas posibles elige algunas aleatoriamente
	 * @param it numero de la iteracion en la que nos encontramos
	 * @return aristas de la iteracion
	 */
	public static Set<Arista> generarSetAristas(int it) {
		Set<Arista> aristas = new HashSet<Arista>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j ++) {
				if(generarRandom(p)) {
					Arista a = new Arista(i,j);
					aristas.add(a);
				}
			}
		}
		
		new GenerarGephi(aristas).generaFicheros(it);
		return aristas;
	}

	/**
	 * Metodo que da aleatoriamente un boolean, usando la probabilidad "p" que el usuario eligio
	 * @param p probabilidad
	 * @return indica si o no
	 */
	public static boolean generarRandom(double p){
		Random r= new Random();
		Double result = r.nextDouble();
		return result <= p;
	}
}
