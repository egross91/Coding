import java.io.*;
import java.util.*;
import java.math.*;

class Main {
	private static BufferedReader in;
	private static ArrayList<BigInteger> factors;
	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		buildFactorials();
		
		int T = Integer.parseInt(in.readLine());
		int N;
		while (T-- > 0) {
			N = Integer.parseInt(in.readLine());
			System.out.println(factors.get(N));
		}
	}
	
	public static void buildFactorials() {
		factors = new ArrayList<BigInteger>();
		factors.add(BigInteger.valueOf(1));
		factors.add(BigInteger.valueOf(1));
		for (int i = 2; i <= 100; ++i) 
			factors.add(factors.get(i-1).multiply(BigInteger.valueOf(i)));
	}
}