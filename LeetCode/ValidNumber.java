import java.math.*;

public class Solution {
    private static final String invalidChars = "abcdfghijklmnopqrstuvwxyzABCDFGHIJKLMNOPQRSTUVWXYZ ";
    
    public boolean isNumber(String s) {
        return check(s.trim());
    }
    
    private boolean check(String s) {
        for (int i = 0; i < invalidChars.length(); ++i) {
            if (s.indexOf(invalidChars.charAt(i)) != -1)
                return false;
        }
        
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) { }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) { }
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) { }
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) { }
        
        
        
        return false;
    }
}