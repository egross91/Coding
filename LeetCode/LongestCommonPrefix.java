public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        
        StringBuilder builder = new StringBuilder();
        String first          = strs[0];
        
        for (int i = 0; i < first.length(); ++i) {
            char val = first.charAt(i);
            
            for (int j = 1; j < strs.length; ++j) 
                if (strs[j].length() <= i || strs[j].charAt(i) != val)
                    return builder.toString();
            
            builder.append(val);
        }
        
        
        return builder.toString();
    }
}