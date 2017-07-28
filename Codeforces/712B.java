import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String S = in.readLine();

		System.out.println(solve(S));
	}

	private static int solve(final String S) {
		if ((S.length() & 0x1) == 1) {
			return -1;
		}

		int x = 0, y = 0;

		for (int i = 0; i < S.length(); ++i) {
			char step = S.charAt(i);

			switch (step) {
				case 'L':
					--x;
					break;
				case 'R':
					++x;
					break;
				case 'U':
					--y;
					break;
				case 'D':
					++y;
					break;
				default:
					System.exit(-1);
			}
		}

		x = Math.abs(x);
		y = Math.abs(y);

		if (x == 0 && y == 0) {
			return 0;
		}
		else if (x == 0 || y == 0) {
			return (Math.max(x+1, y+1) >> 1);
		}

		return ((x + y) >> 1);
	}
}