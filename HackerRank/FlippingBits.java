import java.io.*;
import java.util.*;
// AC
public class Solution {
    private static long TO_XOR = 0xffffffff00000000L;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        
        while (T-- > 0) {
            long value = Long.parseLong(in.readLine());

            System.out.println(((~value) ^ TO_XOR));
        }
    }
}