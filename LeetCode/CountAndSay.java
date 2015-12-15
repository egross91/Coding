public class Solution {
    public String countAndSay(int n) {
        String current = "1";
        
        while (n-- > 1) 
            current = getNextSequence(current);
        
        return current;
    }
    
    private String getNextSequence(final String s) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char value = s.charAt(i);
            int count  = 1;
            
            ++i;
            while (i < s.length() && value == s.charAt(i)) {
                ++count; ++i;
            }
            
            builder.append(count);
            builder.append(value);
        }
        
        return builder.toString();
    }
}