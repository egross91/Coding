import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        
        int n = Integer.parseInt(in.readLine());
        
        for (int i = 0; i < n; ++i) {
            int value = Integer.parseInt(in.readLine());
            
            out.append(String.format("%s%n", solve(value)));
        }
        
        System.out.print(out);
    }
    
    private static String solve(int candidate) {
        if (candidate == 1) { return "Not prime"; }
        if (candidate == 2) { return "Prime"; }
        
        int LIMIT = (int)Math.sqrt(candidate);
        
        for (int i = 2; i <= LIMIT; ++i) {
            if ((candidate % i) == 0) {
                return "Not prime";
            }
        }
        
        return "Prime";
    }
}
