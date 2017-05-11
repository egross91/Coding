import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        in.readLine(); // Ignore the n, m inputs because they're not needed.
        
        String[] magazineWords = in.readLine().split(" ");
        Map<String, Integer> wordCounts = getWordCounts(magazineWords);
        String[] ransomWords = in.readLine().split(" ");
        
        System.out.println(canMakeNote(ransomWords, wordCounts));
    }
    
    private static Map<String, Integer> getWordCounts(String[] words) {
        Map<String, Integer> wordCounts = new HashMap<String, Integer>();
        
        for (String word : words) {
            Integer count = wordCounts.get(word);
            
            count = ((count == null) ? 0 : count);
            
            wordCounts.put(word, count+1);
        }
        
        return wordCounts;
    }
    
    private static String canMakeNote(String[] wordsNeeded, Map<String, Integer> wordCounts) {
        for (String word : wordsNeeded) {
            Integer count = wordCounts.get(word);
            
            if (count == null || count == 0) {
                return "No";
            }
            
            wordCounts.put(word, count-1);
        }
        
        return "Yes";
    }
}
