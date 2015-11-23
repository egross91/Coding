class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        if (nums.size() == 0)
            return 0;
        
        int currentMax = 0; 
        int overallMax = -(1 << 20);
        
        for (int i = 0; i < nums.size(); ++i) {
            currentMax += nums[i];
            overallMax = max(overallMax, currentMax);
            
            if (currentMax < 0) 
                currentMax = 0;
        }
        
        return overallMax;
        
        /** Take 1: **/
        // bool containsPositive = (nums[0] >= 0);
        // int overallMax, runningMax, previous, current, sum;
        // overallMax = runningMax = previous = nums[0];
        
        // // Look through the array.
        // for (int i = 1; i < nums.size(); ++i) {
        //     current = nums[i];
        //     sum     = previous + current;
        //     if (!containsPositive && current >= 0)
        //         containsPositive = true;
            
        //     // Take the current number if the sum is positive (adds to our biggest possible max).
        //     if (sum > 0) 
        //         runningMax += current;
        //     else {
        //         // May have to reset the current tally.
        //         if (current > 0)
        //             runningMax = current;
        //         else {
        //             if (!containsPositive)
        //                 runningMax = current;
        //             else
        //                 runningMax = 0;
        //         }
        //     }
            
        //     overallMax = max(overallMax, max(runningMax, current));
        //     previous = current;
        // }
        
        // return overallMax;
    }
};