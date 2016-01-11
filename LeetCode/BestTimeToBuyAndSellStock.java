public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        
        int maxHere  = 0;
        int maxSoFar = 0;
        
        for (int i = 1; i < prices.length; ++i) {
            int val = prices[i] - prices[i-1];
            
            maxHere  = Math.max(maxHere + val, val);
            maxSoFar = Math.max(maxSoFar, maxHere);
        }
        
        return maxSoFar;
    }
}