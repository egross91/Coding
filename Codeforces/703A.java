import java.util.*;
import java.io.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int n = Integer.parseInt(in.readLine());
		int mishkaWins, chrisWins;
		mishkaWins = chrisWins = 0;

		while (n-- > 0) {
			String[] lineData = in.readLine().split(" ");
			int mishka = Integer.parseInt(lineData[0]);
			int chris = Integer.parseInt(lineData[1]);

			if (mishka > chris)
				++mishkaWins;
			else if (chris > mishka)
				++chrisWins;
		}

		if (mishkaWins > chrisWins)
			System.out.println("Mishka");
		else if (chrisWins > mishkaWins)
			System.out.println("Chris");
		else 
			System.out.println("Friendship is magic!^^");
	}
}