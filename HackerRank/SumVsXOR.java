import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        long n = Long.parseLong(in.readLine());
        
        System.out.println(getCount(n));
    }
    
    private static long getCount(long n) {
        int zeroBitCount = 0;
        long bit = 1;
        
        while (n > 0) {
            if ((n & 0x1) != 1) {
                ++zeroBitCount;
            }
            
            n >>>= 1;
        }
        
        return (long)(bit << zeroBitCount);
    }
}
