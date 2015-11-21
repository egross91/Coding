class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int maxLength = 0;
        int count;
        
        for (int i = 0; i < s.length(); ++i) {
            bool seen[260] = {false};
            int tempInd    = i;
            count          = 0;
            
            while (tempInd < s.length() && !seen[s[tempInd]]) { 
                seen[s[tempInd]] = true;
                ++tempInd;
                ++count;
            }
            
            maxLength = max(maxLength, count);
        }
        
        return maxLength;
    }
};