package practica2.ucm.es;

public class Main {

	public static void main(String[] args) {
		long N, p;
		
		if(args.length < 2) {
			System.out.println("USO: " + args[0] + " <N> <p>");
		} else {
			N = Long.parseLong(args[0]);
			p = Long.parseLong(args[1]);
			
			System.out.println("N: " + N + "  p: " + p);
		}
	}

}
