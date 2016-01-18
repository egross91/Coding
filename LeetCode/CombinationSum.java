public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combos = new LinkedList<List<Integer>>();
        
        if (candidates == null || candidates.length == 0)
            return combos;
        
        Arrays.sort(candidates);
        findCombinations(combos, candidates, new ArrayList<Integer>(), 0, 0, target);
        return combos;
    }
    
    private void findCombinations(List<List<Integer>> combos, final int[] nums, List<Integer> combo, int currNum, int sum, int target) {
        if (sum == target)
            combos.add(combo);
        else if ((sum + nums[currNum]) <= target) {
            for (int i = currNum; i < nums.length; ++i) {
                List<Integer> newCombo = new ArrayList<Integer>(combo);
                newCombo.add(nums[i]);
                
                findCombinations(combos, nums, newCombo, i, sum+nums[i], target);
            }
        }
    }
}