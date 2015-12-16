public class Solution {
    public int countPrimes(int n) {
        final int LIMIT = n;
        boolean[] isPrime = new boolean[LIMIT+1];
        Arrays.fill(isPrime, true);
        
        if (n <= 2)
            return 0;
        
        for (int i = 2; i*2 < LIMIT; ++i)
            isPrime[i*2] = false;
        
        int count = 1;
        for (int i = 3; i < LIMIT; i += 2) {
            if (!isPrime[i])
                continue;
                
            // System.out.printf("prime: %d\n", i);
            ++count;
            for (int m = 2; m*i <= LIMIT; ++m)
                isPrime[m*i] = false;
        }
        
        return count;
    }
}