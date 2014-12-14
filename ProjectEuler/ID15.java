import java.util.*;
import java.io.*;
import java.math.*;

class Main
{
	public static void main(String[] args)
	{
		BigInteger fourtyFactorial = factorial(40);
		BigInteger twentyFactorial = factorial(20);

		BigInteger ans = fourtyFactorial.divide(twentyFactorial.multiply(twentyFactorial));

		System.out.println(ans);
	}

	private static BigInteger factorial(int value) {
		BigInteger ret = BigInteger.valueOf(value);

		while (--value > 0) {
			ret = ret.multiply(BigInteger.valueOf(value));
		}

		return ret;
	}
}
