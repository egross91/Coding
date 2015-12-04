public class Solution {
    public void moveZeroes(int[] nums) {
        final int N   = nums.length;
        int zeroCount = 0;
        int i         = N-1;
        
        while (true) {
            if (i < 0 || (i == 0 && nums[i] != 0)) break;
            
            while (i > 0 && nums[i] != 0) 
                --i;
            
            if (i >= 0 && nums[i] == 0) {
                shiftLeftFromTo(nums, i, N-zeroCount-1);
                nums[N-zeroCount-1] = 0;
                ++zeroCount;
            }
            
            --i;
        }
    }
    
    private void shiftLeftFromTo(int[] nums, int from, int to) {
        for (int i = from; i < to; ++i) {
            nums[i] = nums[i+1];
        }
    }
}