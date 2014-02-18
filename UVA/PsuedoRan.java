import java.util.Scanner;

class Main {
	private static long I;
	private static long L;
	private static long Z;
	private static long M;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int rep = 1;
		while (true) {
			Z = in.nextLong();
			I = in.nextLong();
			M = in.nextLong();
			L = in.nextLong();
			
			if (Z == 0 && I == 0 && M == 0 && L == 0)
				break;
			
			long tort = f(L);
			long hare = f(f(L));
			while (tort != hare) {
				tort = f(tort);
				hare = f(f(hare));
			}
			long ret = 0;
			do {
				tort = f(tort);
				++ret;
			} while (tort != hare);
			System.out.printf("Case %d: %d\n", rep++, ret);
		}
	}
	
	public static long f(long L) {
		return (((Z*L) % M) % M + I) % M;
	}
}