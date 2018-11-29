package modeloAleatorio;

public class Estadisticas {
	private double density;
	private int largestHubDegree;
	private double avgDistance;
	private double avgClustCoefficient;
	private int numAristas;
	private double avgDegree;
	private int connectedComponents;
	
	public Estadisticas(int numAristas, double density,
			int largestHubDegree, double avgDistance, double avgClustCoefficient,
			double avgDegree, int connectedComponents) {
		this.numAristas = numAristas;
		this.density = density;
		this.largestHubDegree = largestHubDegree;
		this.avgDistance = avgDistance;
		this.avgClustCoefficient = avgClustCoefficient;
		this.avgDegree = avgDegree;
		this.connectedComponents = connectedComponents;
	}
	
	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public int getLargestHubDegree() {
		return largestHubDegree;
	}

	public void setLargestHubDegree(int largestHubDegree) {
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

	public double getAvgDegree() {
		return avgDegree;
	}

	public void setAvgDegree(double avgDegree) {
		this.avgDegree = avgDegree;
	}

	public int getConnectedComponents() {
		return connectedComponents;
	}

	public void setConnectedComponents(int connectedComponents) {
		this.connectedComponents = connectedComponents;
	}
}
