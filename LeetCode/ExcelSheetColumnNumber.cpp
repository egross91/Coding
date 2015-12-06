class Solution {
public:
    int titleToNumber(string s) {
        // Keep a multiple of what we need to * the current char by.
        int prod = 0;
        int mult = 1;
        
        if (s.length() > 0)
            prod += (s[s.length()-1] - 'A') + 1;
        
        // Iterate from right to left.
        for (int i = s.length()-2; i >= 0; --i)
            prod += (((s[i] - 'A') + 1) * (pow(26, mult++)));
        
        return prod;
    }
};