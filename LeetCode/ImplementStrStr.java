public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return -1;
        
        for (int i = 0; i <= haystack.length()-needle.length(); ++i) {
            int hi = i;
            int ni = 0;
            
            while ((hi < haystack.length() && ni < needle.length()) && 
                    haystack.charAt(hi) == needle.charAt(ni)) {
                ++hi; ++ni;
            }
            
            if (ni == needle.length())
                return i;
        }
        
        // Needle wasn't found.
        return -1;
    }
}