package modeloBarabasiAlbert;

import org.gephi.statistics.plugin.ClusteringCoefficient;

public class Main {
	public static void main(String[] args) {	
		if(args.length < 2) {
			System.out.println("USO: program <m> <t>");
		} else {
			Integer m = Integer.parseInt(args[0]);
			Integer t = Integer.parseInt(args[1]);
			
			System.out.println("m: " + m + "  t: " + t);
			
			GenerarBarabasiAlbert gen = new GenerarBarabasiAlbert(m.intValue(), t.intValue());
			
			Red r = gen.simularRed();
			
			System.out.println("Red simulada: " + System.getProperty("line.separator") + r);
			
			GenerarCSV csv = new GenerarCSV(r, gen.getN());
			csv.generarFicheros();
			
			System.out.println("Calcular estadisticas:");
			
			Conversor conversor = new Conversor(r);
			
			ClusteringCoefficient cc = conversor.getClusteringCoefficient();
			System.out.println("Average clustering coefficient: " + cc.getAverageClusteringCoefficient());
			
			conversor.export("exportadoConversor.gexf");
			System.out.println("Exportado:  'exportadoConversor.gexf'");
		}	
	}
}
