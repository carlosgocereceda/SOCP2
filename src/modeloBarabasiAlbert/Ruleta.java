package modeloBarabasiAlbert;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Ruleta {
	
	private HashMap<Integer, Integer> grados; //<IDNODO, GARDO>
	
	private HashMap<Double, Integer> ruleta; //<FORMULA, IDNODO>
	private Integer denominador = 0;
	
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

	public Integer barabasi() {
		
		Double pos = 0.0;
		
		for (Map.Entry<Integer, Integer> entry : grados.entrySet()) {
		    pos += entry.getValue() / denominador;
		    ruleta.put(pos, entry.getKey());
		}
		
		Random r = new Random();
		Double random = r.nextDouble();
		
		for(Map.Entry<Double, Integer> entry : ruleta.entrySet()) {
			if(random < entry.getKey()) {
				return entry.getValue();
			}
		}
		return null;
		
	}
	
}
