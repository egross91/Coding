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
        
        String[] lineData = in.readLine().split(" ");
        int N = Integer.parseInt(lineData[0]);
        int D = Integer.parseInt(lineData[1]);
        int[] array = new int[N];
        
        lineData = null; lineData = in.readLine().split(" ");
        for (int i = 0; i < N; ++i) { array[i] = Integer.parseInt(lineData[i]); }
        
        reverse(array, 0, D-1);
        reverse(array, D, N-1);
        reverse(array, 0, N-1);
        
        out.append(array[0]);
        for (int i = 1; i < N; ++i) { out.append(String.format(" %d", array[i])); }
        
        System.out.print(out);
    }
    
    private static void reverse(int[] array, int start, int end) {
        while (start <= end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            ++start; --end;
        }
    }
}
