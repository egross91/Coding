public class Solution {
    public int reverse(int x) {
        long result   = reverseIntToLong(x, 0);
        int intResult = (int) result;
        
        if (result != intResult)
            return 0;
        
        return intResult;
    }
    
    private long reverseIntToLong(int x, long value) {
        if (x == 0)
            return value;
        
        int newX      = x / 10;
        int digit     = x % 10;
        long newValue = (value * 10) + digit;
        
        return reverseIntToLong(newX, newValue);
    }
}