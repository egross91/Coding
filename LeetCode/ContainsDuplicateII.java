public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Integer current;
        
        for (int i = 0; i < nums.length; ++i) {
            current = map.put(nums[i], i);
            
            if (current != null) {
                if ((i - current) <= k) {
                    return true;
                }
            }
        }
        
        return false;
    }
}