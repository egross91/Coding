import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(in.readLine());
        int XOR = 0;
        String[] lineData = in.readLine().split(" ");
        
        for (int i = 0; i < lineData.length; ++i) {
            int value = Integer.parseInt(lineData[i]);
            
            XOR ^= value;
        }
        
        System.out.println(XOR);
    }
}
