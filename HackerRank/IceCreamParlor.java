import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        
        int T = Integer.parseInt(in.readLine());
        
        while (T-- > 0) {
            int money = Integer.parseInt(in.readLine());
            int n     = Integer.parseInt(in.readLine());
            Pair[] costs = new Pair[n];
            String[] lineData = in.readLine().split(" ");
            
            for (int i = 0; i < n; ++i) { costs[i] = new Pair(i, Integer.parseInt(lineData[i])); }
            
            Arrays.sort(costs, Pair.comparator());
                        
            out.append(String.format("%s%n", solve(costs, money)));
        }
        
        System.out.print(out);
    }
    
    private static String solve(Pair[] costs, int money) {
        int v1 = -1;
        int v2 = -1;
        int n  = costs.length;
        
        for (int i = 0; i < n-1; ++i) {
            int foundV2 = binarySearch(i, n, costs, money);
            
            if (foundV2 != -1) {
                v1 = costs[i].index;
                v2 = foundV2;
                break;
            }
        }
        
        if (v1 > v2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        
        return String.format("%d %d", v1+1, v2+1);
    }
    
    private static int binarySearch(int low, int high, Pair[] costs, int money) {
        int firstValue = costs[low].value;
         
        while (low < high) {
            int mid       = (((high - low) >> 1) + low);
            int totalCost = firstValue + costs[mid].value;
            
            if (totalCost == money) {
                return costs[mid].index;
            } else if (totalCost > money) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        
        return -1;
    }
    
    private static class Pair {
        private final int index, value;
        
        public Pair(int i, int v) {
            this.index = i;
            this.value = v;
        }
        
        @Override
        public String toString() {
            return String.format("%d[%d]", this.value, this.index);
        }
        
        public static Comparator<Pair> comparator() {
            return new Comparator<Pair>() {
                @Override
                public int compare(Pair p1, Pair p2) {
                    int valueComparison = Integer.compare(p1.value, p2.value);
                    
                    if (valueComparison != 0) {
                        return valueComparison;
                    } else {
                        return Integer.compare(p1.index, p2.index);
                    }
                }
            };
        }
    }
}
