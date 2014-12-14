import java.util.*;
import java.io.*;
import java.math.*;

class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		int A, B, N;
		A = in.nextInt();
		B = in.nextInt();
		N = in.nextInt();

		ArrayList<BigInteger> fibs = getFibs(A, B, N);

		System.out.println(fibs.get(N-1).toString());
	}

	private static ArrayList<BigInteger> getFibs(int A, int B, int N) {
		ArrayList<BigInteger> ret = new ArrayList<BigInteger>();
		ret.add(BigInteger.valueOf(A));
		ret.add(BigInteger.valueOf(B));

		for (int i = 2; i < N; ++i) {
			BigInteger i1 = ret.get(i-1);
			i1 = i1.multiply(i1);
			BigInteger sum = i1.add((BigInteger)ret.get(i-2));

			ret.add(sum);
		}

		return ret;
	}
}
