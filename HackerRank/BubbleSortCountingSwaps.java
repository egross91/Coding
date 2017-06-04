import java.io.*;
import java.util.*;
// AC
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        String[] lineData = in.readLine().split(" ");
        
        for (int i = 0; i < n; ++i) { arr[i] = Integer.parseInt(lineData[i]); }
        
        System.out.print(solve(n, arr));
    }
    
    private static String solve(int n, int[] arr) {
        int totalSwaps = 0;
        boolean swapped = true;
        
        for (int i = 0; i < n && swapped; ++i) {
            swapped = false;
            
            for (int j = 0; j < n-1; ++j) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    ++totalSwaps;
                    swapped = true;
                }
            }
        }
        
        return String.format("Array is sorted in %d swaps.%nFirst Element: %d%nLast Element: %d", totalSwaps, arr[0], arr[n-1]);
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}