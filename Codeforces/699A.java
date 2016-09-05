import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		String movements = in.readLine();
		int[] particles = new int[N];
		String[] lineData = in.readLine().split(" ");

		for (int i = 0; i < N; ++i) 
			particles[i] = Integer.parseInt(lineData[i]);

		int minTime = Integer.MAX_VALUE;
		for (int i = 1; i < N; ++i) {
			char prev = movements.charAt(i-1);
			char curr = movements.charAt(i);

			if (prev == 'R' && curr == 'L') {
				int time = particles[i] - particles[i-1];
				time /= 2;
				minTime = Math.min(minTime, time);
			}
		}

		System.out.println((minTime == Integer.MAX_VALUE) ? -1 :  minTime);
	}
}