import java.util.*;
import java.io.*;
import java.math.*;

class Main
{
	public static void main(String[] args)
	{
		boolean[] primes = new boolean[775147];
		Arrays.fill(primes, true);
		for (int m = 2; m*2 < 775147; ++m)
			primes[m*2] = false;

		for (int i = 3; i < 775147; ++i) {
			if (!primes[i])
				continue;

			for (int m = 2; m*i < 775147; ++m)
				primes[m*i] = false;
		}

		final BigInteger value = new BigInteger("600851475143");
		for (int i = 775146; i >= 1; --i) {
			if (primes[i]) {
				if (value.mod(BigInteger.valueOf(i)).intValue() == 0) {
					System.out.println(i);
					return;
				}
			}
		}
	}
}
