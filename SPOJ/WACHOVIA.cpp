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

void process(const int&, const int&);

int main(int argc, char** argv) {
    int T, K, M;
    scanf("%i", &T);

    while (T--) {
        scanf("%i %i", &K, &M);

        process(K, M);
    }

    return 0;
}

void process(const int& maxWeight, const int& bags) {
    int dp[55][1005];
    int weight, value, take;

    for (int i = 1; i <= bags; ++i) {
        scanf("%i %i", &weight, &value);

        for (int j = 0; j <= maxWeight; ++j) {
            take = (j-weight > -1) ? dp[i-1][j-weight]+value : 0;
            dp[i][j] = max(take, dp[i-1][j]);
        }
    }

    printf("Hey stupid robber, you can get %i.\n\n", dp[bags][maxWeight]);
}
