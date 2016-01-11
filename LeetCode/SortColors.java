public class Solution {
    private static final int RED   = 0;
    private static final int WHITE = 1;
    private static final int BLUE  = 2;
    
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        
        final int n = nums.length;
        int redPtr  = findFirstNotColorFromIndex(nums, 0, RED, 1);
        if (redPtr == -1)
            return;
        
        for (int i = redPtr+1; i < n; ++i) 
             if (nums[i] == RED)
                swap(nums, redPtr++, i);
                
        int bluePtr = findFirstNotColorFromIndex(nums, n-1, BLUE, -1);
        if (bluePtr == -1)
            return;
        
        for (int i = bluePtr-1; i >= 0; --i)
            if (nums[i] == BLUE)
                swap(nums, bluePtr--, i);
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private int findFirstNotColorFromIndex(int[] arr, int start, int color, int magnitude) {
        for (int i = start; i < arr.length && i >= 0; i += magnitude) 
            if (arr[i] != color)
                return i;
            
        return -1;
    }
}