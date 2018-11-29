package modeloBarabasiAlbert;

public class Estadisticas {
	private double density;
	private Nodo largestHubDegree;
	private double avgDistance;
	private double avgClustCoefficient;
	private int numAristas;
	
	public Estadisticas(int numAristas, double density, Nodo largestHubDegree, double avgDistance, double avgClustCoefficient) {
		this.setNumAristas(numAristas);
		this.density = density;
		this.largestHubDegree = largestHubDegree;
		this.avgDistance = avgDistance;
		this.avgClustCoefficient = avgClustCoefficient;
	}
	
	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public Nodo getLargestHubDegree() {
		return largestHubDegree;
	}

	public void setLargestHubDegree(Nodo largestHubDegree) {
		this.largestHubDegree = largestHubDegree;
	}

	public double getAvgDistance() {
		return avgDistance;
	}

	public void setAvgDistance(double avgDistance) {
		this.avgDistance = avgDistance;
	}

	public double getAvgClustCoefficient() {
		return avgClustCoefficient;
	}

	public void setAvgClustCoefficient(double avgClustCoefficient) {
		this.avgClustCoefficient = avgClustCoefficient;
	}

	public int getNumAristas() {
		return numAristas;
	}

	public void setNumAristas(int numAristas) {
		this.numAristas = numAristas;
	}
}
