public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combos = new LinkedList<List<Integer>>();
        getCombos(combos, new LinkedList<Integer>(), 1, n, k);
        
        return combos;
    }
    
    private void getCombos(List<List<Integer>> combos, List<Integer> combo, int currNum, int n, int k) {
        if (combo.size() == k)
            combos.add(combo);
        else if (currNum <= n) {
            for (int i = currNum; i <= n; ++i) {
                List<Integer> newCombo = new LinkedList<Integer>(combo);
                newCombo.add(i);
                
                getCombos(combos, newCombo, i+1, n, k);
            }
        }    
    }
}