public class Solution {
    public boolean isPalindrome(int x) {
        int reverse = 0;
        int temp    = x;
        int digit;
        
        while (temp > 0) {
            digit = temp % 10;
            temp  /= 10;
            
            reverse = (reverse * 10) + digit;
        }
        
        return (reverse == x);
    }
}