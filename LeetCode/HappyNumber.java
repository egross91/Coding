public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        int value = n;
        
        while (set.add(value)) {
            value = sumDigits(value);
        }
        
        return (value == 1);
    }
    
    private int sumDigits(int num) {
        int digit;
        int sum = 0;
        
        while (num > 0) {
            digit = num % 10;
            sum += (digit * digit);
            num /= 10;
        }
        
        return sum;
    }
}