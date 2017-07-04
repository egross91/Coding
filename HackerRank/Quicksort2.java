import java.io.*;
import java.util.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // Don't care about the input.
        in.readLine();
        
        String[] lineData = in.readLine().split(" ");
        int[] arr = new int[lineData.length];
        
        for (int i = 0; i < lineData.length; ++i) { arr[i] = Integer.parseInt(lineData[i]); }
        
        partition(arr);
    }
    
    private static void partition(int[] arr) {
        boolean isSorted = arrayIsSorted(arr);
        
        if (!isSorted) {
            List<Integer> left  = new ArrayList<Integer>();
            List<Integer> right = new ArrayList<Integer>();
            int p               = arr[0];
            
            for (int i = 1; i < arr.length; ++i) {
                if (arr[i] < p) {
                    left.add(arr[i]);
                }
            }
            
            int[] leftPartition = left.stream().mapToInt(i -> i).toArray();
            partition(leftPartition);
            
            for (int i = 1; i < arr.length; ++i) {
                if (arr[i] > p) {
                    right.add(arr[i]);
                }
            }
            
            int[] rightPartition = right.stream().mapToInt(i -> i).toArray();
            partition(rightPartition);
            
            int aPtr = 0;
            for (int i = 0; i < leftPartition.length; ++i) {
                arr[aPtr++] = leftPartition[i];
            }
            arr[aPtr++] = p;
            for (int i = 0; i < rightPartition.length; ++i) {
                arr[aPtr++] = rightPartition[i];
            }
            
            print(leftPartition);
            print(p);
            if (right.size() > 0) { System.out.print(" "); }
            print(rightPartition);
            System.out.println();
        }
    }
    
    private static boolean arrayIsSorted(int[] arr) {
        if (arr.length < 2) { return true; }
        
        return false;
    }
    
    private static void print(int[] values) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < values.length; ++i) {
            builder.append(String.format("%d ", values[i]));
        }
        
        System.out.print(builder);
    }
    
    private static void print(int value) {
        System.out.printf("%d", value);
    }
}