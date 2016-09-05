import java.io.*;
// AC
class Main {
	private static int twentyFive = 0;
	private static int fifty = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int change = 0;
		String[] lineData = in.readLine().split(" ");

		for (int i = 0; i < n; ++i) {
			int pay          = Integer.parseInt(lineData[i]);
			
			if (pay == 25)
				++twentyFive;
			else if (pay == 50) {
				++fifty;
				if (--twentyFive < 0) {
					System.out.println("NO");
					System.exit(0);
				}
			}
			else {
				if (twentyFive >= 1 && fifty >= 1) {
					--twentyFive;
					--fifty;
				}
				else if (twentyFive >= 3) {
					twentyFive -= 3;
				}
				else {
					System.out.println("NO");
					System.exit(0);
				}
			}

		}

		System.out.println("YES");
	}
}