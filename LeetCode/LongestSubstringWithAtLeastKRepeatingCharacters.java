// AC
public class Solution {
    public int longestSubstring(String s, int k) {
        return longestSubstring(s, k, 0, s.length());
    }
    
    private int longestSubstring(String s, int k, int start, int end) {
        if (start >= end) { return 0; }
        
        int[] charCounts = new int[26];
        
        for (int i = start; i < end; ++i) { charCounts[charToInt(s.charAt(i))]++; }
        
        int low = start;
        
        // Ignore any chars that might be of no use to us.
        while (low < end && charCounts[charToInt(s.charAt(low))] < k) { ++low; }
        
        if (low == end) { return 0; }
        
        // Iterate until we find a char the doesn't satisfy k appearances.
        int high = low;
        
        while (high < end && charCounts[charToInt(s.charAt(high))] >= k) { ++high; }
        
        if (isValidSubstring(s, low, high, k)) {
            return Math.max(high - low, longestSubstring(s, k, high, end));
        } else {
            return Math.max(longestSubstring(s, k, start, low+1), Math.max(longestSubstring(s, k, high, end), longestSubstring(s, k, low, high)));
        }
    }
    
    private boolean isValidSubstring(String s, int low, int high, int k) {
        Set<Character> set = new HashSet<Character>();
        
        for (int i = low; i < high; ++i) { set.add(s.charAt(i)); }
        
        for (Character c : set) {
            int count = 0;
            
            for (int i = low; i < high; ++i) {
                if (s.charAt(i) == c) {
                    ++count;
                }
            }
            
            if (count < k) { return false; }
        }
        
        return true;
    }
    
    private int charToInt(char c) {
        return (int) c - 'a';
    }
}