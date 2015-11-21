#include <iostream>
#include <cstdio>
#include <vector>
#include <deque>
#include <queue>
#include <stack>
#include <map>
#include <set>
#include <list>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

using namespace std;

vector<string> summaryRanges(vector<int>& nums) {
    vector<string> ranges;
        
    vector<int> values = nums;
    sort(values.begin(), values.end());
    
    int lowPtr, highPtr;
    lowPtr = 0;
    
    while (lowPtr < values.size()) {
        int currentValue = values[lowPtr];
        highPtr = lowPtr+1;
        
        while (highPtr < values.size() && values[highPtr] == currentValue+1) {
            currentValue = values[highPtr];
            ++highPtr;
        }
        
        ostringstream ss;
        if (highPtr-1 == lowPtr) {
            ss << currentValue;
            ranges.push_back(ss.str());
        }
        else if (lowPtr < highPtr-1) {
            ss << values[lowPtr] << "->" << currentValue;
            ranges.push_back(ss.str());
        }

        lowPtr = highPtr;
    }
    
    return ranges;
}

int main(int argc, char** argv) {
    // freopen("SummaryRanges.in", "r", stdin);

    int nums[] = { 10, 15, 1, 0, 20, 5, 6, 11, 12, 21, 22, 23, 24, 25 };
    vector<int> n(nums, nums+14);
    // vector<int> n;
    vector<string> results = summaryRanges(n);

    if (results.size() >= 1) {
        cout << results[0];
        for (int i = 1; i < results.size(); ++i)
        	cout << "," << results[i];

        cout << endl << endl;
    }

    return 0;
}
