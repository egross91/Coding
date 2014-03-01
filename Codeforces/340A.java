import java.util.Scanner;

class Main {
	private static int x;
	private static int y;
	private static long max;
	private static long reps_i;
	private static long reps_f;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		x = in.nextInt();
		y = in.nextInt();
		long a = in.nextLong();
		long b = in.nextLong();
		
		max = ((a > b) ? a : b);
		
		reps_i = 1;
		reps_f = 1;
		
		long tort = f_Iahub(x, reps_i++);
		long hare = f_Floyd(y, reps_f++);
		
		while (tort != hare) {
			tort = f_Iahub(x, reps_i++);
			hare = f_Floyd(y, reps_f++);
			// hare = f_Floyd(y, reps_f++);
			// System.out.printf("Tort: %d  Hare:  %d", tort, hare);
		}
		
		long bricks = 0;
		reps_f = 1;
		do {
			hare = f_Floyd(y, reps_f++);
			if (hare >= a && hare <= b)
				++bricks;
		} while (tort != hare);
		System.out.printf("%d", bricks);
	}
	
	public static long f_Iahub(long x, long reps) {
		return ((x-1)*reps) % max;
	}
	public static long f_Floyd(long y, long reps) {
		return ((y-1)*reps) % max;
	}
}
