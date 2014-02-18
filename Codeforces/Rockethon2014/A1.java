import java.util.Scanner;
import java.util.Arrays;

class A1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int q = in.nextInt();
		
		if (q == 1) {
			System.out.printf("%d", 0);
			return;
		}
		
		int[] skis = new int[n];
		for (int i = 0; i < n; ++i)
			skis[i] = in.nextInt();
			
		Arrays.sort(skis);
		
		int packs = 0;
		int low = 0;
		int high = n-1;
		while (low < high) {
			int sum = skis[low]+skis[high];
			if (sum == q) {
				++packs;
				++low;
				--high;
				continue;
			}
			if (sum > q) {
				--high;
				continue;
			}
			if (sum < q) {
				++low;
			}
		}
		
		System.out.printf("%d", packs);
	}
}