public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        Set<Integer> set = new HashSet<Integer>();
        int[] noDupes    = new int[nums.length];
        int pointer      = 0;
        
        for (int i = 0; i < nums.length; ++i) {
            if (set.add(nums[i]) == true) {
                noDupes[pointer++] = nums[i];
            }
        }
        
        for (int i = 0; i < pointer; ++i)
            nums[i] = noDupes[i];
        
        return pointer;
    }
}