import java.util.*;
import java.io.*;
// AC
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] lineData = in.readLine().split(" ");
		int n = Integer.parseInt(lineData[0]);
		int m = Integer.parseInt(lineData[1]);

		int[] cities = new int[n];
		int[] towers = new int[m];
		
		lineData = in.readLine().split(" ");
		for (int i = 0; i < n; ++i)
			cities[i] = Integer.parseInt(lineData[i]);

		lineData = in.readLine(). split(" ");
		for (int i = 0; i < m; ++i)
			towers[i] = Integer.parseInt(lineData[i]);

		System.out.println(solve(n, m, cities, towers));
	}

	private static int solve(final int n, final int m, int[] cities, int[] towers) {
		int high = 2_000_000_000;
		int low  = 0;


		while (high > low) {
			int mid = (low + ((high - low) >> 1));

			boolean works = checkRange(mid, cities, towers);

			if (works)
				high = mid;
			else
				low = mid+1;
		}

		return high;
	}

	private static boolean checkRange(final int r, int[] cities, int[] towers) {
		int cityInd = 0;

		for (int i = 0; i < towers.length; ++i) {
			int currentTower = towers[i];
			
			while (true) {
				int currentCity = cities[cityInd];
				int distance = 0;

				if (currentTower < 0) {
					if (currentCity < 0) 
						distance = Math.abs(currentTower - currentCity);
					else
						distance = currentCity - currentTower;
				}
				else {
					if (currentCity < 0)
						distance = currentTower - currentCity;
					else 
						distance = Math.abs(currentTower - currentCity);
				}

				if (distance <= r)
					++cityInd;
				else
					break;

				if (cityInd == cities.length)
					return true;
			}
		}

		return false;
	}
}