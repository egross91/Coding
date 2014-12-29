#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <map>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>
#include <utility>

using namespace std;

pair<int, int> process(const int&);

int main(int argc, char** argv) {
    int T, N;
    scanf("%i", &T);

    for (int t = 1; t <= T; ++t) {
        scanf("%i", &N);

        pair<int, int> ans = process(N);
        cout << "Case " << t << ": " << ans.first << " " << ans.second << endl;
    }

    return 0;
}

pair<int, int> process(const int& N) {
    vector<int> heights(N);
    for (int i = 0; i < N; ++i)
        scanf("%i", &heights[i]);

    pair<int, int> jumps(0, 0);
    int previous = heights[0];
    for (int i = 1; i < N; ++i) {
        if (heights[i] > previous)
            jumps.first++;
        else if (heights[i] < previous)
            jumps.second++;

        previous = heights[i];
    }

    return jumps;
}
