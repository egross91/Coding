public class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null)
            return 0;
        
        // Create a map to keep a count of all the elements that we may see.
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        // Create a pointer to the current element.
        int current;
        // Create a variable for Math.floor(n / 2).
        final int goal = nums.length / 2;
        
        // Iterate through all the nums.
        for (int i = 0; i < nums.length; ++i) {
            // Get current count of current element from the map.
            current = (counts.get(nums[i]) == null) ? 0 : counts.get(nums[i]);
            
            // Increment.
            // Check for count > Math.floor(n / 2).
            if (++current > goal)
                return nums[i];
            
            // Put it back into the map.
            counts.put(nums[i], current);
        }
        
        return nums[0];
    }
}