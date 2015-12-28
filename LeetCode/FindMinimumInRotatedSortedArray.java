public class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int low  = 0;
        int high = nums.length-1;
        while (low < high) {
            int mid = (low + ((high - low) >> 1));
            // System.out.printf("low: %d\tmid: %d\thigh: %d\n", low, mid, high);
            
            if (nums[mid] > nums[low]) {
                if (nums[mid] > nums[high])
                    low = mid;
                else 
                    high = mid;
            }
            else { // (nums[mid] <= nums[low])
                if (nums[mid] < nums[high])
                    high = mid;
                else
                    low = mid+1;
            }
        }
        
        return nums[low];
    }
}