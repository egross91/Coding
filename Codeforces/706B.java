import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int[] prices = new int[N];
		String[] lineData = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			prices[i] = Integer.parseInt(lineData[i]);

		Arrays.sort(prices);

		int Q = Integer.parseInt(in.readLine());
		for (int i = 0; i < Q; ++i) {
			int index = binarySearch(prices, Integer.parseInt(in.readLine()));

			out.append(String.format("%d%n", index));
		}

		System.out.print(out);
	}

	private static int binarySearch(final int[] prices, int bread) {
		final int N = prices.length-1;
		int low  = 0;
		int high = N;

		while (low <= high) {
			int mid = (low + ((high - low) >> 1));

			if (prices[mid] > bread) 
				high = mid-1;
			else 
				low = mid+1;
		}

		return low;
	}
}