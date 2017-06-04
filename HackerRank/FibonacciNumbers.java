import java.util.*;
// AC
public class Solution {
    private static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    
    public static int fibonacci(int n) {
        if (memo.get(n) != null) {
            return memo.get(n);
        }
        
        memo.put(n, fibonacci(n-1) + fibonacci(n-2));
        
        return memo.get(n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        
        // Base cases.
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);
        
        System.out.println(fibonacci(n));
    }
}
