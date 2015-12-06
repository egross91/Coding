class Solution {
public:
    int climbStairs(int n) {
        vector<int> numberOfWays;
        numberOfWays.push_back(1);
        numberOfWays.push_back(1);
        
        for (int i = 2; i <= n; ++i)
            numberOfWays.push_back(numberOfWays[i-2] + numberOfWays[i-1]);
            
        return numberOfWays[n];
    }
};