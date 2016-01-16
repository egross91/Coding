public class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int low  = 0;
        int high = nums.length-1;
        
        while (low < high) {
            int mid     = (low + ((high - low) >> 1));
            int lowVal  = (mid-1 < 0)            ? Integer.MIN_VALUE : nums[mid-1];
            int highVal = (mid+1 >= nums.length) ? Integer.MIN_VALUE : nums[mid+1];
            
            if (nums[mid] < lowVal && nums[mid] >= highVal)
                high = mid-1;
            else if (nums[mid] > lowVal && nums[mid] > highVal)
                return mid;
            else
                low = mid+1;
        }
        
        return low;
    }
}