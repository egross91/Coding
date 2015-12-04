public class Solution {
    private static final int ZERO = 0;
    private static final int ONE  = 1;
    
    public int[] singleNumber(int[] nums) {
        int xor       = getXor(nums);
        int setBitNum = (xor & ~(xor-1));
        int[] twoXor  = new int[2];
        
        for (int i = 0; i < nums.length; ++i) {
            if ((setBitNum & nums[i]) != 0) 
                twoXor[ONE]  ^= nums[i];
            else
                twoXor[ZERO] ^= nums[i];
        }
        
        return twoXor;
    }
    
    private int getXor(int[] nums) {
        int bitMask = 0;
        
        for (int i = 0; i < nums.length; ++i)
            bitMask ^= nums[i];
        
        return bitMask;
    }
}