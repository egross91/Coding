public class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> appearances = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; ++i) {
            int value = ((appearances.get(nums[i]) != null) ? appearances.get(nums[i]) : 0);
            
            ++value;
            appearances.put(nums[i], value);
        } 
        
        for (Map.Entry<Integer, Integer> entry : appearances.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }
        
        return -1;
    }
}