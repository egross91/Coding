import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long sum = 0;
        
        in.readLine(); // Read n, but doesn't matter.
        String[] lineData = in.readLine().split(" ");
        
        for (String value : lineData) {
            sum += Long.parseLong(value);
        }
        
        System.out.println(sum);
    }
}