import java.io.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		final int N = Integer.parseInt(in.readLine());
		String[] lineData = in.readLine().split(" ");
		int sum = 0;
		int players = N >> 1; // N / 2
		int[] nums = new int[N];

		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(lineData[i]);
			sum += nums[i];
		}

		int goalSum = sum / players;
		boolean[] used = new boolean[N];
		for (int i = 0; i < N; ++i) {
			if (used[i]) continue;

			int goalComplement = goalSum - nums[i];
			for (int k = i+1; k < N; ++k) {
				if (used[k]) continue;
				if (goalComplement == nums[k]) {
					used[k] = used[i] = true;
					out.append(String.format("%d %d%n", (i+1), (k+1)));
					break;
				}
			}
		}

		System.out.print(out);
	}
}