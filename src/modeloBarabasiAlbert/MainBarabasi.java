package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;

public class MainBarabasi {
	private static JFrame frame;
	private static BarabasiPanel panel;
	
	public static int m;
	public static int t;
	public static int numIteraciones = 10;

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("USO: program <m> <t> <numIteraciones>");
			System.out.println("Iniciando modo GUI por defecto...");
			
			frame = new JFrame();
			frame.setTitle("Analisis de Redes Sociales");
			frame.setSize(1000, 650);
			panel = new BarabasiPanel();
			frame.setContentPane(panel);
			frame.setVisible(true);
		} else {
			MainBarabasi.m = Integer.parseInt(args[0]);
			MainBarabasi.t = Integer.parseInt(args[1]);

			if(args.length > 2)
				MainBarabasi.numIteraciones = Integer.parseInt(args[2]);
			
			MainBarabasi.comenzar();
		
			//GenerarBarabasiAlbert gen = new GenerarBarabasiAlbert(m, t);
			//Red r = gen.simularRed();
			//ConversorGephiToolkit conversor = new ConversorGephiToolkit(r);
			//conversor.export("exportadoConversor.gexf");
			//System.out.println("Exportado:  'exportadoConversor.gexf'");
		}
	}
	// Metodo llamado desde la vista(GUI) en el actionPerformed
	public static void comenzar() {
		System.out.println("m " + m);
		System.out.println("t" + t);
		simular(m, t, numIteraciones);
	}

	// Metodo que hara los calculos correspondientes para Barabasi
	public static void simular(int m, int t, int iteraciones) {
		// Declaramos la lista que tendra la informacion de cada iteracion
		// para mas tarde hacer la correspondiente media de todas las iteraciones
		List<Estadisticas> estadisticas = new ArrayList<>();

		// Bucle que realizara las correspondientes iteraciones
		for (int i = 0; i < iteraciones; i++) {
			GenerarBarabasiAlbert gen = new GenerarBarabasiAlbert(m, t);
			Red r = gen.simularRed();
			
			// (REVISAR COMENTARIO)
			// Utilizamos una herramiente de JAVA, para convertir los datos de Gephi
			ConversorGephiToolkit conversor = new ConversorGephiToolkit(r);
			GraphDensity gd = conversor.getDensity();
			GraphDistance gdis = conversor.getGraphDistance();
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();
			
			// Pintamos por pantalla la informacion resultante de calcular
			// las distintas métricas de Barabasi
			String info = System.getProperty("line.separator") + "Simulacion numero: " + (i + 1) + " terminada";
			panel.escribe(info);
			frame.repaint();
			info = "Num. Aristas: " + r.numAristas();
			panel.escribe(info);
			info = "Density: " + gd.getDensity();
			panel.escribe(info);
			info = "Largest Hub Degree: " + r.getLargestHubDegree();
			panel.escribe(info);
			info = "Avg. Distance: " + gdis.getPathLength();
			panel.escribe(info);
			info = "Average clustering coefficient: " + cc.getAverageClusteringCoefficient();
			
			panel.escribe(info);
			frame.update(frame.getGraphics());
			
			// Generamos el correspondiente CSV
			GenerarCSV csv = new GenerarCSV(r, gen.getN());
			csv.generarFicheros(i+1);
			//conversor.export("exportadoConversor.gexf");
			
			// Añado los nuevos datos a la lista
			estadisticas.add(new Estadisticas(r.numAristas(), gd.getDensity(), r.getLargestHubDegree(), gdis.getPathLength(),
					cc.getAverageClusteringCoefficient()));
			
		}
		// Hemos acabado las iteraciones
		
		// Ahora calcularemos la media de todas las medidas tomadas durante
		// las iteraciones
		double sNumAristas = 0, avgNumAristas = 0, sDensity = 0, avgDensity = 0, sAvgDistance = 0, avgDistance = 0, sAvgClustCoefficient = 0, avgClustCoefficient = 0, sLargestHubDegree = 0, avgLargestHubDegree = 0;
		
		// Recorremos la lista con todas las estadisticas tomadas
		// durante las iteraciones
		for (Estadisticas e : estadisticas) {
			sNumAristas += e.getNumAristas();
			sDensity += e.getDensity();
			sLargestHubDegree += e.getLargestHubDegree().getDegree();
			sAvgDistance += e.getAvgDistance();
			sAvgClustCoefficient += e.getAvgClustCoefficient();
		}
		// Hacemos la media aritmetica de todos los datos
		avgNumAristas = sNumAristas / iteraciones;
		avgDensity = sDensity / iteraciones;
		avgLargestHubDegree = sLargestHubDegree / iteraciones;
		avgDistance = sAvgDistance / iteraciones;
		avgClustCoefficient = sAvgClustCoefficient / iteraciones;
		
		// Los pintamos por el JLabel correspondiente de la aplicación
		String info = "TOTAL";
		panel.escribe(info);
		info = "Num. Aristas: " + avgNumAristas;
		panel.escribe(info);
		info = "Density: " + avgDensity;
		panel.escribe(info);
		info = "Largest Hub Degree: " + avgLargestHubDegree;
		panel.escribe(info);
		info = "Avg. Distance: " + avgDistance;
		panel.escribe(info);
		info = "Average clustering coefficient: " + avgClustCoefficient;
		panel.escribe(info);
		frame.update(frame.getGraphics());
		System.out.println("Num. Aristas: " + avgNumAristas);
		System.out.println("Density: " + avgDensity);
		System.out.println("Largest Hub Degree: " + avgLargestHubDegree);
		System.out.println("Avg. Distance: " + avgDistance);			
		System.out.println("Average clustering coefficient: " + avgClustCoefficient);
	}
}
