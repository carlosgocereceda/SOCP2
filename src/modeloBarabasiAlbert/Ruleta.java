package modeloBarabasiAlbert;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Ruleta {

	private HashMap<Integer, Integer> grados = new HashMap<>(); // <IDNODO, GRADO>

	private Map<Double, Integer> ruleta = new TreeMap<>(); // <FORMULA, IDNODO>
	private Integer denominador = 0;

	@SuppressWarnings("unlikely-arg-type")
	public Ruleta(Red red) {
		for (int i = 0; i < red.getAristas().size(); i++) {
			if (!grados.containsKey(red.getAristas().get(i).getNodo1())) {
				grados.put(red.getAristas().get(i).getNodo1().getValue(),
						red.getAristas().get(i).getNodo1().getValue());
			}
			if (!grados.containsKey(red.getAristas().get(i).getNodo2())) {
				grados.put(red.getAristas().get(i).getNodo2().getValue(),
						red.getAristas().get(i).getNodo2().getValue());
			}
		}
		for (Map.Entry<Integer, Integer> entry : grados.entrySet()) {
			denominador += entry.getValue();
		}
	}

	public Integer barabasi() {
		Double pos = 0.0;

		for (Map.Entry<Integer, Integer> entry : grados.entrySet()) {
			pos += (entry.getValue() * 1.0) / (denominador * 1.0);
			ruleta.put(pos, entry.getKey());
		}

		Random r = new Random();
		Double random = r.nextDouble();

		for (Map.Entry<Double, Integer> entry : ruleta.entrySet()) {
			if (random < entry.getKey()) {
				return entry.getValue();
			}
		}

		System.err.println("No se ha encontrado el nodo con probabilidad: " + random + " en la ruleta: ");
		for (Map.Entry<Double, Integer> entry : ruleta.entrySet()) {
			System.err.println("Nodo: " + entry.getValue() + ", Probabilidad: " + entry.getKey());
		}
		
		return null;
	}

}
