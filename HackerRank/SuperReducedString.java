import java.io.*;
import java.util.*;
// AC 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String data = in.readLine();
        
        System.out.print(solve(data));
    }
    
    private static String solve(String data) {
        StringBuilder builder = new StringBuilder(data);
        boolean needsProcessing = true;
        
        while (needsProcessing) {
            String currentState = builder.toString();
            needsProcessing = false;
            
            if (currentState.length() < 2) { continue; }
            
            for (int i = 0; i < currentState.length()-1; ++i) {
                if (currentState.charAt(i) == currentState.charAt(i+1)) {
                    needsProcessing = true;
                    builder.deleteCharAt(i+1);
                    builder.deleteCharAt(i);
                    break;
                }
            }
        }
        
        String result = builder.toString();
        
        if (result.equals("")) {
            return "Empty String";
        } else {
            return result;
        }
    }
}