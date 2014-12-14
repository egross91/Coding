import java.util.*;
import java.io.*;

class Main {
	private static final int LIMIT = 77000000;

	public static void main(String[] args) {
		ArrayList<Integer> primes = getPrimes();

		int current = 55;
		int interval = 11;
		while (current < LIMIT) {
			current += interval++;
			if (countDivisor(current, primes) > 500) {
				System.out.println(current);
				return;
			}
		}
	}

	private static ArrayList<Integer> getPrimes() {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		boolean[] sieve = new boolean[LIMIT];

		Arrays.fill(sieve, true);

		primes.add(2);
		for (int i = 3; i < LIMIT; i += 2) {
			if (!sieve[i] || (i & 0x1) == 0)
				continue;

			primes.add(i);
			for (int m = 2; m*i < LIMIT; ++m)
				sieve[m*i] = false;
		}

		return primes;
	}

	private static int countDivisor(int value, ArrayList<Integer> primes) {
		int temp = value;

		int total = 1;
		int sqrt = (int)Math.sqrt(value);
		for (int i = 0; (int)primes.get(i) < sqrt+1; ++i) {
			int prime = primes.get(i);

			Pair current = new Pair(i, 0);
			while ((temp % prime) == 0) {
				current.exp++;
				temp /= prime;
			}

			if (current.exp > 0)
				total *= (current.exp+1);
		}

		return total;
	}
}

class Pair {
	public int num;
	public int exp;

	public Pair() {
		this.num = this.exp = 0;
	}

	public Pair(int num, int exp) {
		this.num = num;
		this.exp = exp;
	}
}
