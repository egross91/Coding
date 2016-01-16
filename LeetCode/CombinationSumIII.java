public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> numbers = new LinkedList<List<Integer>>();
        findCombinations(k, n, 0, 0, new LinkedList<Integer>(), numbers);
        
        return numbers;
    }
    
    private void findCombinations(int k, int n, int count, int currNum, List<Integer> combo, List<List<Integer>> combos) {
        if (count == k) {
            if (sum(combo) == n)
                combos.add(combo);
        }
        else if (count < k && (currNum+1) <= 9) {
            for (int i = currNum+1; i <= 9; ++i) {
                List<Integer> newCombo = new LinkedList(combo);
                
                newCombo.add(i);
                findCombinations(k, n, count+1, i, newCombo, combos);
            }
        }
    }
    
    private int sum(List<Integer> nums) {
        int sum = 0;
        
        for (Integer val : nums)
            sum += val;
        
        return sum;
    }
}