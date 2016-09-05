import java.io.*;
// AC
class Main {
	private static String[] loveHate = { "I love", "I hate" };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int n = Integer.parseInt(in.readLine());

		out.append(loveHate[1]);

		for (int i = 2; i <= n; ++i)
			out.append(String.format(" that %s", loveHate[i % 2]));

		out.append(" it");

		System.out.println(out);
	}
}