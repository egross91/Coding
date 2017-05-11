import java.io.*;
import java.util.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int L = Integer.parseInt(in.readLine());
        int R = Integer.parseInt(in.readLine());
        int max = Integer.MIN_VALUE;
        
        for (int i = L; i < R; ++i) {
            for (int k = L+1; k <= R; ++k) {
                max = Math.max(max, i^k);
            }
        }
        
        System.out.println(max);
    }
}