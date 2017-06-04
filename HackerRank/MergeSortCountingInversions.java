import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        
        int d = Integer.parseInt(in.readLine());
        
        while (d-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] arr = new int[n];
            String[] lineData = in.readLine().split(" ");
            
            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(lineData[i]);
            }
            
            out.append(String.format("%d%n", solve(arr)));
        }
        
        System.out.print(out);
    }
    
    private static long solve(int[] arr) {
        return mergeSort(arr, new int[arr.length], 0, arr.length-1);
    }
    
    private static long mergeSort(int[] arr, int[] temp, int lower, int upper) {
        long inversions = 0;
        
        if (lower < upper) {
            int mid = (((upper - lower) >> 1) + lower);
            
            inversions += mergeSort(arr, temp, lower, mid);
            inversions += mergeSort(arr, temp, mid+1, upper);
            inversions += merge(arr, temp, lower, upper);
        }
        
        return inversions;
    }
    
    private static long merge(int[] arr, int[] temp, int lower, int upper) {
        int mid         = (((upper - lower) >> 1) + lower);
        int left        = lower;
        int right       = mid+1;
        int tempPtr     = lower;
        int swaps       = 0; // Count number of swaps already done, because we could end up double counting them.
        long inversions = 0;
        
        while (left <= mid && right <= upper) {
            if (arr[left] > arr[right]) {
                inversions += (right-left-swaps++);
                temp[tempPtr] = arr[right];
                right++;
            } else {
                temp[tempPtr] = arr[left];
                left++;
            }
            
            tempPtr++;
        }
        
        while (left <= mid) {
            temp[tempPtr++] = arr[left++];
        }
        while (right <= upper) {
            temp[tempPtr++] = arr[right++];
        }
        
        for (int i = lower; i <= upper; ++i) {
            arr[i] = temp[i];
        }
        
        return inversions;
    }
}
