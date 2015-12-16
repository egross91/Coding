public class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        
        if (prices.length <= 1)
            return profit;
        
        int previous = prices[0];
        int current;
        for (int i = 1; i < prices.length; ++i) {
            current = prices[i];
            profit += Math.max(current-previous, 0);
            previous = current;
        }
        
        return profit;
    }
}