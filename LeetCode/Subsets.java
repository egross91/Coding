public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subs = new LinkedList<List<Integer>>();
        
        if (nums == null)
            return subs;
        
        Arrays.sort(nums);
        int goalBitmask = (1 << nums.length) - 1;
        for (int i = goalBitmask; i >= 0; --i) {
            List<Integer> subset = new ArrayList<Integer>();
            int bitmask = i;
            int index   = 0;
            
            while ((1 << index) <= bitmask) {
                if (((1 << index) & bitmask) != 0) 
                    subset.add(nums[index]);
                ++index;
            }
            
            subs.add(subset);
        }
        
        return subs;
    }
}