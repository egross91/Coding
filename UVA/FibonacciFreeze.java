import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	private static ArrayList<BigInteger> fibs = new ArrayList<BigInteger>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        compute();

        String line = null;
        String answer;
        int currentFib;
        while ((line = in.readLine()) != null) {
        	currentFib = Integer.parseInt(line.trim());

        	answer = String.format("The Fibonacci number for %d is %s%n", currentFib, fibs.get(currentFib));
        	// System.out.println(answer);
           	builder.append(answer);
        }

        System.out.print(builder);
    }

    private static void compute() {
    	fibs.add(BigInteger.ZERO);
    	fibs.add(BigInteger.ONE);
    	BigInteger current;

    	for (int i = 2; i <= 5000; ++i) {
    		current = fibs.get(i-2).add(fibs.get(i-1));

    		fibs.add(current);
    	}
    }
}