package modeloAleatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;

public class Main {

	public static long N;
	public static double p;
	
	public static void main(String[] args) {	
		if(args.length < 2) {
			System.out.println("USO: " + args[0] + " <N> <p>");
		} else {
			N = Long.parseLong(args[0]);
			p = Double.parseDouble(args[1]);
			
			System.out.println("N: " + N + "  p: " + p);
			
			
			simular(10, false);
			
			// Exportar un ejemplo
			new GenerarGephi(generarAristas()).generaFicheros();
		}
		
		
	}
	
	public static void simular(int iteraciones, boolean generaGephi) {
		List<Estadisticas> estadisticas = new ArrayList<>();

		System.out.println("Comenzando la simulacion de una red aleatoria de N = " + N + " y p =" + p);

		for (int i = 0; i < iteraciones; i++) {
			List<Arista> aristas = generarAristas();
			
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(aristas);

			GraphDensity gd = conversor.getDensity();
			GraphDistance gdis = conversor.getGraphDistance();
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();

			estadisticas.add(new Estadisticas(aristas.size(), gd.getDensity(), conversor.getLargestHubDegree(), gdis.getPathLength(),
					cc.getAverageClusteringCoefficient(),
					conversor.getDegree().getAverageDegree(),
					conversor.getConnectedComponents().getConnectedComponentsCount()));
			
			if(generaGephi)
				new GenerarGephi(aristas).generaFicheros();
			
			System.out.println("Simulacion numero: " + (i + 1) + " terminada.");
		}
		
		double sNumAristas = 0, avgNumAristas = 0, sDensity = 0, avgDensity = 0, sAvgDistance = 0, avgDistance = 0, sAvgClustCoefficient = 0, avgClustCoefficient = 0, sLargestHubDegree = 0, avgLargestHubDegree = 0,
				sAvgDegree = 0, avgDegree = 0, sConnectedComponents = 0, avgConnectedComponents = 0;
		
		for (Estadisticas e : estadisticas) {
			sNumAristas += e.getNumAristas();
			sDensity += e.getDensity();
			sLargestHubDegree += e.getLargestHubDegree();
			sAvgDistance += e.getAvgDistance();
			sAvgClustCoefficient += e.getAvgClustCoefficient();
			sAvgDegree += e.getAvgDegree();
			sConnectedComponents += e.getConnectedComponents();
		}
		
		avgNumAristas = sNumAristas / iteraciones;
		avgDensity = sDensity / iteraciones;
		avgLargestHubDegree = sLargestHubDegree / iteraciones;
		avgDistance = sAvgDistance / iteraciones;
		avgClustCoefficient = sAvgClustCoefficient / iteraciones;
		avgDegree = sAvgDegree / iteraciones;
		avgConnectedComponents = sConnectedComponents / iteraciones;
		
		System.out.println("Num. Aristas: " + avgNumAristas);
		System.out.println("Density: " + avgDensity);
		System.out.println("Largest Hub Degree: " + avgLargestHubDegree);
		System.out.println("Avg. Distance: " + avgDistance);			
		System.out.println("Average clustering coefficient: " + avgClustCoefficient);
		System.out.println("Degree: " + avgDegree);
		System.out.println("Connected components: " + avgConnectedComponents);
	}
	
	public static List<Arista> generarAristas() {
		List<Arista> pares = new ArrayList<>();
		List<Arista> aristas = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Arista a = new Arista(i, j);
				if(!pares.contains(a) && i != j)
					pares.add(a);
			}
		}
		
		if(pares.size() != ((N * (N - 1) ) / 2))
			System.out.println("Numero de pares: " + pares.size() + " debian ser: " + ((N * (N - 1) ) / 2));
		
		for (int i = 0; i < pares.size(); i++) {
			if(generarRandom(p))
				aristas.add(pares.get(i));
		}
		return aristas;
	}

	public static boolean generarRandom(double p){
		Double result;
		Random r= new Random();
		result = r.nextDouble();
		
		return result <= p;
	}
}
