#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <map>
#include <list>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

using namespace std;

int N;

string swap(string str, int i) {
    char temp = str[i];
    str[i] = str[N-1];
    str[N-1] = temp;

    return str;
}

void process(const string& str) {
    string longest;
    int curr, last = str[N-1]-'0', lo = -1;
    for (int i=0; i<N-1; ++i) {
        curr = int(str[i]-'0');
        if (!(curr & 0x1)) {
            if (curr < last) {
                printf("%s\n", swap(str, i).c_str());
                return;
            }
            else if (curr > last)
                lo = i;
        }
    }

    if (longest == "" && lo == -1) {
        printf("-1\n");
        return;
    }
    if (lo != -1)
        longest = swap(str, lo);

    printf("%s\n", longest.c_str());
}

int main(int argc, char** argv) {
    string line;
    // freopen("508B.in", "r", stdin);

    while (cin >> line) {
        N = line.length();
        process(line);
    }

    return 0;
}
