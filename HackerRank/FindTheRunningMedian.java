import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        int[] a = new int[n];
        
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        
        System.out.print(solve(a));
    }
    
    private static String solve(int[] arr) {
        MaxHeap maxHeap       = new MaxHeap();
        MinHeap minHeap       = new MinHeap();
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < arr.length; ++i) {
            int value = arr[i];
            
            if (minHeap.top() == -1) {
                minHeap.add(value);
            } else if (maxHeap.top() == -1) {
                maxHeap.add(value);
            } else {
                // Keep the heaps in valid order.
                if (maxHeap.top() > minHeap.top()) {
                    int temp = maxHeap.pop();
                    maxHeap.add(minHeap.pop());
                    minHeap.add(temp);
                }
                
                if (value > maxHeap.top() && value < minHeap.top()) {
                    if (minHeap.size() >= maxHeap.size()) {
                        maxHeap.add(value);
                    } else {
                        minHeap.add(value);
                    }
                } else if (value < maxHeap.top() && value < minHeap.top()) {
                    if (maxHeap.size() > minHeap.size()) {
                        minHeap.add(maxHeap.pop());
                    }
                    
                    maxHeap.add(value);
                } else { // (value > maxHeap.top() && value > minHeap.top())
                    if (minHeap.size() > maxHeap.size()) {
                        maxHeap.add(minHeap.pop());
                    }
                    
                    minHeap.add(value);
                }
            }
            
            if (((maxHeap.size() + minHeap.size()) & 0x1) == 1) {
                if (maxHeap.size() > minHeap.size()) {
                    builder.append(String.format("%.1f%n", (double)maxHeap.top()));
                } else {
                    builder.append(String.format("%.1f%n", (double)minHeap.top()));
                }
            } else {
                builder.append(String.format("%.1f%n", (double)((double)(maxHeap.top() + minHeap.top()) / 2.0D)));
            }
        }
        
        return builder.toString();
    }
    
    private static abstract class Heap {
        private PriorityQueue<Integer> heap;
        
        public Heap(Comparator<Integer> comp) {
            this.heap = new PriorityQueue<Integer>(comp);
        }
        
        public void add(int value) {
            this.heap.add(value);
        }
        
        public int top() {
            return ((this.heap.peek() == null) ? -1 : this.heap.peek());
        }
        
        public int pop() {
            return this.heap.poll();
        }
        
        public int size() {
            return this.heap.size();
        }
    }
    
    private static class MinHeap extends Heap {
        public MinHeap() {
            super(new Comparator<Integer>() {
                @Override
                public int compare(Integer left, Integer right) {
                    return Integer.compare(left, right);
                }
            });
        }
    }
    
    private static class MaxHeap extends Heap {
        public MaxHeap() {
            super(new Comparator<Integer>() {
                @Override
                public int compare(Integer left, Integer right) {
                    return Integer.compare(right, left);
                }
            });
        }
    }
}
