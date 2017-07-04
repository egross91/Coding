// AC
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        MaxHeap heap = new MaxHeap(k);
        
        for (int value : nums) {
            heap.push(value);
        }
        
        int min = Integer.MAX_VALUE;
        
        while (!heap.isEmpty() && k-- > 0) {
            min = Math.min(min, heap.pop());
        }
        
        return min;
    }
    
    private class MaxHeap {
        private PriorityQueue<Integer> Q;
        
        public MaxHeap(int size) {
            this.Q = new PriorityQueue<Integer>(size, new Comparator<Integer>() {
                @Override
                public int compare(Integer l, Integer r) {
                    return r - l;
                }
            });
        }
        
        public Integer pop() {
            return this.Q.poll();
        }
        
        public boolean isEmpty() {
            return this.Q.isEmpty();
        }
        
        public void push(int v) {
            this.Q.offer(v);
        }
        
        public Integer top() {
            return this.Q.peek();
        }
    }
}