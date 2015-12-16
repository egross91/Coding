public class NumArray {
    private int[] arr;
    
    public NumArray(int[] nums) {
        arr = new int[nums.length+1];
        
        for (int i = 1; i <= nums.length; ++i)
            addValue(i, nums[i-1]);
    }

    public int sumRange(int i, int j) {
        int low  = Math.min(i, j);
        int high = Math.max(i, j);
        
        return (query(high+1) - query(low));
    }
    
    private int query(int i) {
        int x   = i;
        int sum = 0;
        
        while (x > 0) {
            sum += arr[x];
            x   -= (x & (-x));
        }
        
        return sum;
    }
    
    private void addValue(int i, int v) {
        int x = i;
        
        while (x < arr.length) {
            arr[x] += v;
            x      += (x & (-x));
        }
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);