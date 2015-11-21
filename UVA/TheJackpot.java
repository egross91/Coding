import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	private static int N;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        
        while (true) {
        	N = in.nextInt();
        	if (N == 0) break;

        	int[] bets = new int[N];
        	for (int i = 0; i < N; ++i)
        		bets[i] = in.nextInt();

        	int most = kadane(bets);
        	if (most < 1) {
        		builder.append("Losing streak.\n");
        	} else {
        		builder.append(String.format("The maximum winning streak is %d.\n", most));
        	}
        }

        System.out.print(builder);
    }

    private static int kadane(final int[] bets) {
    	int maxSoFar = bets[0];
    	int maxHere  = bets[0];

    	for (int i = 1; i < bets.length; ++i) {
    		maxHere  = Math.max(bets[i], maxHere + bets[i]);
    		maxSoFar = Math.max(maxSoFar, maxHere);
    	}

    	return maxSoFar;
    }
}
