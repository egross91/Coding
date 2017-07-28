// AC
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < 1) { return new int[0]; }
        
        MaxHeap heap = new MaxHeap();
        int[] maxes  = new int[nums.length-k+1];
        int maxPtr   = 0;
        
        for (int i = 0; i < k; ++i) {
            heap.add(new DataPointer(nums[i], i));
        }
        
        maxes[maxPtr++] = heap.peek().value;
        
        for (int i = k; i < nums.length; ++i) {
            heap.remove(new DataPointer(nums[i-k], i-k));
            heap.add(new DataPointer(nums[i], i));
            maxes[maxPtr++] = heap.peek().value;
        }
        
        return maxes;
    }
    
    private class DataPointer {
        public final int value, index;
        
        public DataPointer(int v, int i) {
            this.value = v;
            this.index = i;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DataPointer)) { return false; }
            
            DataPointer odp = (DataPointer) o;
            
            return (odp.value == this.value && odp.index == this.index);
        }
    }
    
    private class MaxHeap {
        private PriorityQueue<DataPointer> queue;
        
        public MaxHeap() {
            this.queue = new PriorityQueue<DataPointer>(new Comparator<DataPointer>() {
                @Override
                public int compare(DataPointer l, DataPointer r) {
                    int valueComparison = r.value - l.value;
                    
                    if (valueComparison == 0) {
                        return l.index - r.index;
                    }
                    
                    return valueComparison;
                }
            });
        }
        
        public void remove(DataPointer dp) {
            this.queue.remove(dp);
        }
        
        public void add(DataPointer dp) {
            this.queue.offer(dp);
        }
        
        public DataPointer peek() {
            return this.queue.peek();
        }
    }
}