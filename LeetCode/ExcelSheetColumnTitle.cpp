class Solution {
public:
    string convertToTitle(int n) {
        string result;
        buildColumnTitle(n, 1, result);
        
        return result;
    }
    
    void buildColumnTitle(int n, unsigned multipleOfTwentySix, string& str) {
        if (n >= (multipleOfTwentySix * 26) + multipleOfTwentySix) 
            buildColumnTitle(n-1, (multipleOfTwentySix*26), str);
        
        int charVal = (((n/multipleOfTwentySix) - 1) % 26) + 1;
        // printf("charVal: %d\n", charVal);
        str.push_back((charVal-1) + 'A');
    }
};