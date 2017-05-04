import java.io.*;
import java.util.*;
// AC - this problem is trash.
public class Solution {
    private static final int MAX_N = 100_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        
        int N = Integer.parseInt(in.readLine());
        String[] arrayData = in.readLine().split(" ");
        int[] array = new int[N];
        
        for (int i = 0; i < N; ++i) { array[i] = Integer.parseInt(arrayData[i]); }
        
        // Arrays.sort(array); // O(N log N)
        
        // System.out.println(solve(array, N)); // O(N)
        System.out.println(solveLinear(array, N));
    }
    
    private static int solveLinear(int[] array, int N) {
        int max       = getMax(array);
        int[] buckets = new int[max+5];
        
        // Build buckets - Counting Sort.
        for (int i = 0; i < N; ++i) buckets[array[i]]++;
        
        // Find count.
        int count = 0;
        
        for (int i = 0; i < max; ++i) {
            // Find a filled bucket.
            while (buckets[i] == 0) { ++i; } // Nothing is here and that's bad.
            
            int lastFilledBucket = i; // Use as pointer for loop iterations.
            
            // Find the next value that doesn't have an empty bucket - if at all.
            for (int j = i+1; j <= i+4; ++j) {
                if (buckets[j] != 0) {
                    lastFilledBucket = j;
                }
            }
            
            i = lastFilledBucket;
            ++count;
        }
        
        return count;
    }
    
    private static int getMax(int[] array) {
        int max = Integer.MIN_VALUE;
        
        for (Integer val : array) {
            max = Math.max(val, max);
        }
        
        return max;
    }
    
    private static int solve(int[] array, int N) {
        int count = 0;
        
        for (int i = 0; i < N; ++i) {
            int candidate = array[i];
            int j         = i+1;
            
            while (j < N && array[j] <= candidate+4) { ++j; }
            
            i = j-1;
            ++count;
        }
        
        
        return count;
    }
}