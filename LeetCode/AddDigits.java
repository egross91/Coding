public class Solution {
    public int addDigits(int num) {
        int result = ((num-1) % 9) + 1;
        
        return result;
    }
}