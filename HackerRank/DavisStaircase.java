import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    private static Map<Integer, Long> memoization = new HashMap<Integer, Long>();
    
    static {
        memoization.put(1, 1L);
        memoization.put(2, 2L);
        memoization.put(3, 4L);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int s = Integer.parseInt(in.readLine());

        for(int a0 = 0; a0 < s; a0++){
            int n = Integer.parseInt(in.readLine());
            
            System.out.println(solve(n, 0));
        }
    }
    
    private static long solve(int n, long sum) {
        if (memoization.get(n) != null) {
            return memoization.get(n) + sum;
        } else {
            long value = solve(n-1, sum) + solve(n-2, sum) + solve(n-3, sum);
            
            memoization.put(n, value);
            
            return value + sum;
        }
    }
}
