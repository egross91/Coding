public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        generatePermutations(nums, nums.length-1, set);
        
        for (List<Integer> perm : set) 
            results.add(perm);
        
        return results;
    }
    
    private void generatePermutations(int[] arr, int n, Set<List<Integer>> set) {
        if (n == 0)
            set.add(convertToList(arr));
        else {
            for (int i = 0; i <= n; ++i) {
                swap(arr, i, n);
                generatePermutations(arr, n-1, set);
                swap(arr, i, n);
            }
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private List<Integer> convertToList(final int[] arr) {
        List<Integer> result = new ArrayList(arr.length);
        
        for (int i = 0; i < arr.length; ++i)
            result.add(arr[i]);
        
        return result;
    }
}