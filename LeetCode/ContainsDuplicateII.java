// AC
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> appearances = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; ++i) {
            Integer index = appearances.get(nums[i]);
            
            if (index == null) {
                appearances.put(nums[i], i);
            } else {                
                if ((i - index) <= k) {
                    return true;
                }
                
                appearances.put(nums[i], i);
            }
        }
        
        return false;
    }
}