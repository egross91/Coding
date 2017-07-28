// AC
public class Solution {
    public int trap(int[] height) {
        if (height.length == 0) { return 0; }
        
        int result   = 0;
        int low      = getFirstNonZero(height, 0, 1);
        int high     = getFirstNonZero(height, height.length-1, -1);
        
        if (low+1 >= high) { return 0; }
        
        int maxLeft  = height[low];
        int maxRight = height[high];
        
        while (low+1 < high) {
            if (maxLeft <= maxRight) {
                int value = height[++low];
                
                if (value > maxLeft) {
                    maxLeft = value;
                } else {
                    result += (maxLeft - value);
                }
            } else {
                int value = height[--high];
                
                if (value > maxRight) {
                    maxRight = value;
                } else {
                    result += (maxRight - value);
                }
            }
        }
                
        return result;
    }
    
    private int getFirstNonZero(int[] arr, int start, int delta) {
        while (start >= 0 && start < arr.length && arr[start] == 0) {
            start += delta;
        }
        
        return start;
    }
}