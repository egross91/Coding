public class Solution {
    public boolean isPalindrome(String s) {
        String data = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        int hi = data.length()-1;
        int lo = 0;
        
        while (lo < hi) 
            if (data.charAt(lo++) != data.charAt(hi--))
                return false;
        
        return true;
    }
}