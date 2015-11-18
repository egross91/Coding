import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Product.out")));

		solve(in);
	}

	public static void solve(BufferedReader in) throws IOException {
		String line;
		StringBuilder answers = new StringBuilder();
		BigInteger x, y;

		while ((line = in.readLine()) != null) {
			x = new BigInteger(line);
			y = new BigInteger(in.readLine());

			answers.append(x.multiply(y) + "\n");
			x = y = null;
		}

		System.out.print(answers);
	}
}