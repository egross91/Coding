import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] lineData = in.readLine().split(" ");

		int cities = Integer.parseInt(lineData[0]);
		int capitals = Integer.parseInt(lineData[1]);

		long sum = 0;
		int[] costs = new int[cities+1];
		lineData = in.readLine().split(" ");
		for (int i = 1; i <= cities; ++i) {
			costs[i] = Integer.parseInt(lineData[i-1]);
			sum += costs[i];
		}

		boolean[] isCapital = new boolean[cities+1];
		long totalBeauty = 0;
		int currentCapital;
		int capitalCost;
		lineData = in.readLine().split(" ");
		for (int i = 0; i < capitals; ++i) {
			currentCapital = Integer.parseInt(lineData[i]);
			isCapital[currentCapital] = true;
			capitalCost = costs[currentCapital];
			totalBeauty += capitalCost * (sum - capitalCost);

			sum -= capitalCost;
		}

		for (int i = 1; i < cities; ++i) {
			if (isCapital[i])
				continue;

			if (!isCapital[i + 1]) {
				totalBeauty += (costs[i] * costs[i+1]);
			}
			else 
				++i;
		}

		if (!isCapital[1] && !isCapital[cities]) 
			totalBeauty += (costs[cities] * costs[1]);

		System.out.println(totalBeauty);
	}
}