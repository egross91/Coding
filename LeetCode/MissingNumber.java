public class Solution {
    public int missingNumber(int[] nums) {
        final int N    = nums.length;
        final int goal = (((N+1) * N) >> 1);
        int sum        = 0;
        
        for (int i = 0; i < nums.length; ++i)
            sum += nums[i];
        
        return (goal-sum);
    }
}