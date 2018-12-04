package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.List;

import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;

public class Main {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("USO: program <m> <t>");
		} else {
			Integer m = Integer.parseInt(args[0]);
			Integer t = Integer.parseInt(args[1]);

			System.out.println("m: " + m + "  t: " + t);
			
			simular(m, t, 100);
			
			GenerarBarabasiAlbert gen = new GenerarBarabasiAlbert(m, t);
			Red r = gen.simularRed();
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(r);
			conversor.export("exportadoConversor.gexf");
			System.out.println("Exportado:  'exportadoConversor.gexf'");
		}
	}

	public static void simular(int m, int t, int iteraciones) {
		List<Estadisticas> estadisticas = new ArrayList<>();

		for (int i = 0; i < iteraciones; i++) {
			GenerarBarabasiAlbert gen = new GenerarBarabasiAlbert(m, t);
			Red r = gen.simularRed();
			
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(r);

			GraphDensity gd = conversor.getDensity();
			GraphDistance gdis = conversor.getGraphDistance();
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();

			estadisticas.add(new Estadisticas(r.numAristas(), gd.getDensity(), r.getLargestHubDegree(), gdis.getPathLength(),
					cc.getAverageClusteringCoefficient()));
			
		}
		
		double sNumAristas = 0, avgNumAristas = 0, sDensity = 0, avgDensity = 0, sAvgDistance = 0, avgDistance = 0, sAvgClustCoefficient = 0, avgClustCoefficient = 0, sLargestHubDegree = 0, avgLargestHubDegree = 0;
		
		for (Estadisticas e : estadisticas) {
			sNumAristas += e.getNumAristas();
			sDensity += e.getDensity();
			sLargestHubDegree += e.getLargestHubDegree().getDegree();
			sAvgDistance += e.getAvgDistance();
			sAvgClustCoefficient += e.getAvgClustCoefficient();
		}
		
		avgNumAristas = sNumAristas / iteraciones;
		avgDensity = sDensity / iteraciones;
		avgLargestHubDegree = sLargestHubDegree / iteraciones;
		avgDistance = sAvgDistance / iteraciones;
		avgClustCoefficient = sAvgClustCoefficient / iteraciones;
		
		System.out.println("Num. Aristas: " + avgNumAristas);
		System.out.println("Density: " + avgDensity);
		System.out.println("Largest Hub Degree: " + avgLargestHubDegree);
		System.out.println("Avg. Distance: " + avgDistance);			
		System.out.println("Average clustering coefficient: " + avgClustCoefficient);
	}
}
