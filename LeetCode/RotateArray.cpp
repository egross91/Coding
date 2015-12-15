class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        int pivot = std::max(abs((int)(nums.size()-(k % nums.size()))), 0);

        reverse(nums.begin(), nums.begin()+pivot);
        reverse(nums.begin()+pivot, nums.end());
        reverse(nums.begin(), nums.end());
    }
};