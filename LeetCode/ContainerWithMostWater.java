// AC
public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) { return 0; }
        
        int bestProduct = findBestProduct(height);
        
        reverse(height);
        
        bestProduct = Math.max(bestProduct, findBestProduct(height));
        
        return bestProduct;
    }
    
    public int findBestProduct(int[] height) {
        int low              = 0;
        int high             = height.length-1;
        int currentMaxHeight = Math.max(height[low], height[high]);
        int bestProduct      = Math.min(height[low], height[high]) * high;
        
        while (low < high) {
            while (low < high && height[low] <= currentMaxHeight) {
                bestProduct = Math.max(bestProduct, Math.min(height[low], height[high]) * (high-low));
                
                ++low;
            }
            
            if (low >= high) { break; }
            
            bestProduct      = Math.max(bestProduct, Math.min(height[low], height[high]) * (high-low));
            currentMaxHeight = height[low];
            
            while (high > low && height[high] <= currentMaxHeight) {
                bestProduct = Math.max(bestProduct, Math.min(height[low], height[high]) * (high-low));
                
                --high;
            }
            
            if (low >= high) { break; }
            
            bestProduct      = Math.max(bestProduct, Math.min(height[low], height[high]) * (high-low));
            currentMaxHeight = height[high];
        }
        
        return bestProduct;
    }
    
    private void reverse(int[] arr) {
        int low = 0;
        int high = arr.length-1;
        
        while (low < high) {
            int temp  = arr[low];
            arr[low++]  = arr[high];
            arr[high--] = temp;
        }
    }
}