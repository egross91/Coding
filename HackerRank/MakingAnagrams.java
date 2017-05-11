import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static int numberNeeded(String first, String second) {
        int[] firstLetterCount  = new int[26];
        int[] secondLetterCount = new int[26];
        int deletionCount       = 0;
        
        for (char c : first.toCharArray()) {
            int characterIndex = (int) (c-'a');
            firstLetterCount[characterIndex]++;
        }
        
        for (char c : second.toCharArray()) {
            int characterIndex = (int) (c-'a');
            secondLetterCount[characterIndex]++;
        }
        
        for (int i = 0; i < 26; ++i) {
            deletionCount += Math.abs(firstLetterCount[i] - secondLetterCount[i]);
        }
        
        return deletionCount;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
