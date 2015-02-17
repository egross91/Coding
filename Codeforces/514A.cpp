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
#include <climits>
#include <algorithm>

using namespace std;

void solve(const string&);

int main(int argc, char** argv) {
    // freopen("514A.in", "r", stdin);
    string line;
    while (cin >> line) {
        solve(line);
    }

    return 0;
}

void solve(const string& str) {
    int largest = 4;
    string ans = str;
    for (unsigned long long i = 0; i < str.length(); ++i) {
        int curr = int(str[i]-'0');
        if (curr > largest) {
            if (i == 0 && curr != 9)
                ans[i] = char((9-curr) + '0');
            else if (i != 0)
                ans[i] = char((9-curr) + '0');
        }
    }

    printf("%s\n", ans.c_str());
}
