class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> vals = nums;
        sort(vals.begin(), vals.end());
        
        int low  = 0;
        int high = vals.size()-1;
        int candidate;
        
        while (low < high) {
            candidate = vals[low] + vals[high];
            
            if (candidate == target)
                break;
            else if (candidate < target) 
                ++low;
            else if (candidate > target)
                --high;
        }
        
        int lowIndex = -1, highIndex = -1;
        int lowVal  = vals[low];
        int highVal = vals[high];
        
        for (int i = 0; i < nums.size(); ++i) {
            if (lowIndex == -1 && nums[i] == lowVal)
                lowIndex = i;
            else if (highIndex == -1 && nums[i] == highVal)
                highIndex = i;
            
            if (lowIndex != -1 && highIndex != -1)
                break;
        }
        
        vector<int> indices;
        if (lowIndex > highIndex) {
            int temp = lowIndex;
            lowIndex = highIndex;
            highIndex = temp;
        }
        
        indices.push_back(lowIndex+1);
        indices.push_back(highIndex+1);
        
        return indices;
    }
};