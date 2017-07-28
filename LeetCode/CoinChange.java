// AC
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null) { return -1; }
        if (amount == 0) { return 0; }
        
        int[] dp = new int[amount+1]; 
        
        for (int i = 0; i < coins.length; ++i) {
            int coin = coins[i];
            
            for (int j = coin; j <= amount; ++j) {
                if (dp[j] == 0) {
                    if (j % coin == 0) {
                        dp[j] = Math.min(j / coin, Math.max(dp[j-coin] + dp[coin], 1));
                    } else if (dp[j-coin] != 0) {
                        dp[j] = dp[j-coin] + dp[coin];
                    }
                } else {
                    if (dp[j-coin] != 0) {
                        dp[j] = Math.min(dp[j], Math.min(dp[j-coin] + dp[coin], (j/coin) + dp[j-coin]));
                    } else {
                        if (j % coin == 0) {
                            dp[j] = Math.min(dp[j], j / coin);
                        }
                    }
                }
            }
            
            System.out.println(Arrays.toString(dp));
        }
        
        System.out.println(Arrays.toString(dp));
        
        return (dp[amount] == 0) ? -1 : dp[amount];
        
        // TLE
        // int result = coinChange(coins, amount, 0, 0, Integer.MAX_VALUE);
        
        // return (result == Integer.MAX_VALUE) ? -1 : result;
    }
    
    private int coinChange(int[] coins, int amount, int coinPtr, int used, int best) {
        if (amount == 0) {
            return Math.min(best, used);
        }
        
        if (coinPtr < coins.length) {
            best = Math.min(best, coinChange(coins, amount, coinPtr+1, used, best));
            
            while (amount >= coins[coinPtr]) {
                amount -= coins[coinPtr];
                
                ++used;
                
                best = Math.min(best, coinChange(coins, amount, coinPtr+1, used, best));
            }
            
            return Math.min(best, coinChange(coins, amount, coinPtr+1, used, best));
        } else {
            return best;
        }
    }
}