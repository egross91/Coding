class Solution {
public:
    bool isAnagram(string s, string t) {
        map<char,int> sMap, tMap;
        
        for (int i = 0; i < s.length(); ++i)
            sMap[s[i]]++;
        for (int i = 0; i < t.length(); ++i)
            tMap[t[i]]++;
        
        
        return sMap == tMap;
    }
};