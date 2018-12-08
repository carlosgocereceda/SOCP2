package modeloAleatorio;

import java.awt.BorderLayout;
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
	
	private static JFrame frame;
	private static AleatorioPanel panel;
	
	public static void main(String[] args) {	
		frame = new JFrame();
		frame.setTitle("Analisis de Redes Sociales");
		frame.setSize(1000, 650);
		panel = new AleatorioPanel();
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.WEST);
		frame.setVisible(true);
		if(args.length < 2) {
			System.out.println("USO: program <N> <p>");
		} else {
			//N = Long.parseLong(args[0]);
			//p = Double.parseDouble(args[1]);
			
			//System.out.println("N: " + N + "  p: " + p);
			
			//simular(10);
			
			// Exportar un ejemplo
			//new GenerarGephi(generarSetAristas()).generaFicheros();
		}
	}
	
	public static void comenzar() {
		simular(10);
	}
	
	public static void simular(int iteraciones) {
		List<Estadisticas> estadisticas = new ArrayList<>();

		System.out.println("Comenzando la simulacion de una red aleatoria de N = " + N + " y p =" + p);

		for (int i = 0; i < iteraciones; i++) {
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(generarSetAristas(i));

			GraphDensity gd = conversor.getDensity();
			GraphDistance gdis = conversor.getGraphDistance();
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();

			Estadisticas e = new Estadisticas(conversor.getNumAristas(), gd.getDensity(), conversor.getShortestHubDegree(), conversor.getLargestHubDegree(),gdis.getPathLength(),
					cc.getAverageClusteringCoefficient(),
					conversor.getDegree().getAverageDegree(),
					conversor.getConnectedComponents().getConnectedComponentsCount());
			
			estadisticas.add(e);
			
			
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
	
	public static Set<Arista> generarSetAristas(int it) {
		Set<Arista> pares = new HashSet<Arista>();
		Set<Arista> aristas = new HashSet<Arista>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				Arista a = new Arista(i, j);
				Arista a2 = new Arista(j, i);
				if(i != j && !pares.contains(a) && !pares.contains(a2))
					pares.add(a);
			}
		}
		
		if(pares.size() != ((N * (N - 1) ) / 2))
			System.out.println("Numero de pares: " + pares.size() + " debian ser: " + ((N * (N - 1) ) / 2));
		
		for (Arista a : pares) {
			if(generarRandom(p))
				aristas.add(a);
		}
		new GenerarGephi(aristas).generaFicheros(it);
		
		return aristas;
	}

	public static boolean generarRandom(double p){
		Double result;
		Random r= new Random();
		result = r.nextDouble();
		
		return result <= p;
	}
}
