public class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        
        // Iterate n-2 through the array.
        int i;
        for (i = 1; i < nums.length; ++i)  {
            if ((nums[i-1] < target) && (nums[i] > target))
                return i;
            else if (nums[i] == target)
                return i;
            else if (nums[i-1] == target || target < nums[i-1])
                return (i-1);
        }
        
        if (nums[i-1] < target)
            return i;
        return i-1;
    }
}