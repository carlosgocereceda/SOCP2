package modeloBarabasiAlbert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ruleta {
	
	private HashMap<Integer, Integer> grados;
	private Integer denominador;
	
	@SuppressWarnings("unlikely-arg-type")
	public Ruleta(Red red) {
		for(int i = 0; i < red.getAristas().size(); i++) {
			if(!grados.containsKey(red.getAristas().get(i).getNodo1())) {
				grados.put(red.getAristas().get(i).getNodo1().getValue(), red.getAristas().get(i).getNodo1().getValue());
			}
			if(!grados.containsKey(red.getAristas().get(i).getNodo2())) {
				grados.put(red.getAristas().get(i).getNodo2().getValue(), red.getAristas().get(i).getNodo2().getValue());
			}
		}
		for (Map.Entry<Integer, Integer> entry : grados.entrySet()) {
		    denominador += entry.getValue();
		}
	}

	public void barabasi(Red red) {
		int denominador = 0;
		ArrayList<Double> ruleta = new ArrayList<Double>();
		int k = 0;
		ruleta.add(k, 0.0);
		for (Map.Entry<Integer, Integer> entry : grados.entrySet()) {
		    ruleta.add(entry.getKey(), entry.getValue());
		}
		
	}
}
