class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        // Keep a pointer to the end of the array to move that element into the index
        // that is to be overriden.
        int high = nums.size() - 1;
        
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == val) {
                if (i < high) {
                    high = findRightIndexOfNotVal(nums, val, high);
                    if (high == -1 || high <= i) break;
                    
                    nums[i]      = nums[high];
                    nums[high--] = val;
                }
            }
        }
        
        int index;
        for (index = 0; index < nums.size(); ++index)
            if (nums[index] == val) break;
        
        // Return the length of the array minus the count of the 'val'.
        return index;
    }
    
private:
    int findRightIndexOfNotVal(const vector<int>& arr, int val, int start) {
        for (int i = start; i >= 0; --i) {
            if (arr[i] != val) {
                return i;
            }
        }
        
        return -1;
    }
};