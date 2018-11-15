package modeloBarabasiAlbert;

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
			
		}	
	}
}
