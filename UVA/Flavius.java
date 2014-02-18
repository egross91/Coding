import java.util.Scanner;

class Main {
	private static long N;
	private static long a;
	private static long b;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			N = in.nextLong();
			if (N == 0)
				break;
				
			a = in.nextLong();
			b = in.nextLong();
			long tort = f(0);
			long hare = f(f(0));
			long ret = N;
			while (tort != hare) { // Find the cycle
				tort = f(tort);
				hare = f(f(hare));
			}
			do { // Find the length of the cycle
				hare = f(hare);
				--ret;
			} while(hare != tort);
			System.out.printf("%d\n", ret);
		}
	}
	
	public static long f(long x) {
		return ((a * (x*x%N)) % N + b) % N;
	}
}