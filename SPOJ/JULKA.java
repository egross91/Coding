import java.util.*;
import java.math.*;

class Main {
	private static Scanner input = new Scanner(System.in);
	private static final BigInteger TWO = BigInteger.valueOf(2);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 10; ++i) 
			sb.append(solve() + "\n");

		System.out.println(sb);
	}

	public static String solve() {
		BigInteger total = input.nextBigInteger();
		BigInteger diff = input.nextBigInteger();

		BigInteger klaudia = (total.add(diff)).divide(BigInteger.valueOf(2));
		BigInteger natalia = total.subtract(klaudia);

		// klaudia = klaudia.add(diff.divide(TWO));
		// natalia	= natalia.subtract(diff.divide(TWO));

		// if (diff.mod(TWO) != BigInteger.valueOf(0))
		// 	natalia = natalia.subtract(BigInteger.valueOf(1));

		return klaudia.toString() + "\n" + natalia.toString();
	}
}