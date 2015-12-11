public class Solution {
    public int trailingZeroes(int n) {
        int zeros = findTrailingZeroes(n, 5, 1);
        
        return zeros;
    }
    
    private int findTrailingZeroes(int n, long multipleOfFive, int zeroesToAdd) {
        int count = 0;
        
        if (n >= multipleOfFive) 
            return ((n/(int)multipleOfFive) * zeroesToAdd) + findTrailingZeroes(n , multipleOfFive*5l, zeroesToAdd++);
        
        return count;
    }
}
