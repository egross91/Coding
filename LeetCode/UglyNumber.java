public class Solution {
    public boolean isUgly(int num) {
        if (num <= 0)
            return false;
        
        int result;
        
        result = divideByWhileZero(num, 2);
        result = divideByWhileZero(result, 3);
        result = divideByWhileZero(result, 5);
        
        return (result == 1);
    }
    
    private int divideByWhileZero(int val, int divisor) {
        while ((val % divisor) == 0) {
            val /= divisor;
        }
        
        return val;
    }
}