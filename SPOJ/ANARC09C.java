import java.util.*;

class Main 
{
	private static final int THOUSAND = 1000;
	
	private static boolean[] buildSieve;
	private static ArrayList<Integer> primes;
	public static void main(String[] args)
	{
		//Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(System.in);
		primeSieve(); // Build Sieve
		fillPrimes();
		
		int tests = 1;
		while (true)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			if (a == 0 && b == 0) 
				return;
			ArrayList<Pair> numberOne = new ArrayList<Pair>();
			ArrayList<Pair> numberTwo = new ArrayList<Pair>();
			
			primeFactorize(numberOne, a);
			primeFactorize(numberTwo, b);
			
			HashSet<Integer> cardinality = new HashSet<Integer>();
			
			// Find X and D
			int D = 0;
			int index_o = 0;
			int index_t = 0;
			while (index_o < numberOne.size() && index_t < numberTwo.size()) {
				Pair one = numberOne.get(index_o);
				Pair two = numberTwo.get(index_t);
				if (one.first == two.first) 
				{
					D += Math.abs(one.second - two.second);
					++index_o;
					++index_t;
					cardinality.add(one.first);
				}
				else if (one.first < two.first) 
				{
					D += one.second;
					++index_o;
					cardinality.add(one.first);
				}
				else if (one.first > two.first)
				{
					D += two.second;
					++index_t;
					cardinality.add(two.first);
				}
			}
			
			while (index_o < numberOne.size()) 
			{
				Pair one = numberOne.get(index_o++);
				D += one.second;
				cardinality.add(one.first);
			}
			
			while (index_t < numberTwo.size())
			{
				Pair two = numberTwo.get(index_t++);
				D += two.second;
				cardinality.add(two.first);
			}
			
			System.out.println(tests++ + ". " + cardinality.size() + ":" + D);
		}
	}
	
	/** BUILD PRIME SIEVE **/
	public static void primeSieve()
	{
		buildSieve = new boolean[THOUSAND+1];
		Arrays.fill(buildSieve, true);
		buildSieve[0] = buildSieve[1] = false;
		
		for (int i = 2; i <= THOUSAND; ++i) {
			if (!buildSieve[i])
				continue;
			
			for (int m = 2; m*i <= THOUSAND; ++m)
				buildSieve[m*i] = false;
		}
	}
	
	public static void fillPrimes()
	{
		primes = new ArrayList<Integer>();
		for (int i = 0; i <= THOUSAND; ++i) {
			if (buildSieve[i])
				primes.add(i);
		}
		primes.add(Integer.MAX_VALUE); // Avoid OutOfBounds
	}
		
	/** CHECK TO SEE IF THE CURRENT NUMBER IS A PRIME **/
	/* public static boolean isPrime(int number)
	{
		if ((number & 0x1) == 0 && number != 2) // Even number
			return false;
		
		return primes[number];
	} */
	
	/** FIND THE PRIME FACTORS OF theNumber **/
	public static void primeFactorize(ArrayList<Pair> number, int theNumber)
	{
		// Get all the prime factorizations of both numbers
		// up to the max of them both, to later compare cardinality.
		int limit = (int)Math.sqrt(theNumber);
		for (int i = 0; (int)primes.get(i) <= limit; ++i) {
			if (theNumber % primes.get(i) == 0) {
				Pair p = new Pair(primes.get(i), 0);
				while (theNumber % primes.get(i) == 0) {
					p.second++;
					theNumber /= primes.get(i);
				}
				number.add(p);
			}
		}
		if (theNumber > 1)
			number.add(new Pair(theNumber, 1));
		
		
		/* if (theNumber == 1) 
		{
			number.add(new Pair(2,0));
			return;
		}
		
		int i = 0;
		while (theNumber > 1 && i < primes.size()) {
			int prime = (int)primes.get(i++);
			if (theNumber % prime == 0) {
				Pair p = new Pair(prime,0);
				while (theNumber % prime == 0) {
					p.second++;
					theNumber /= prime;
				}
				number.add(p);
			}
		} */
	}
}

class Pair 
{
	public int first; // Prime number
	public int second; // Exponent of that number
	Pair(int first, int second)
	{
		this.first = first;
		this.second = second;
	}
}
