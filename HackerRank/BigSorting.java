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
        String[] values = new String[n];
        for (int i = 0; i < n; ++i) { values[i] = in.readLine(); }
        
        Arrays.sort(values, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int lengthComparison = s1.length() - s2.length();
                
                if (lengthComparison == 0) {
                    return s1.compareTo(s2);
                } else {
                    return lengthComparison;
                }
            }
        });
        
        StringBuilder builder = new StringBuilder();
        
        for (String value : values) {
            builder.append(String.format("%s%n", value));
        }
        
        System.out.print(builder);
    }
}
