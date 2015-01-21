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
    int B, P;

    while (scanf("%i %i", &B, &P) == 2 && (B || P))
        process(B, P);

    return 0;
}

void process(const int& budget, const int& N) {
    int dp[105][505] = { 0 };

    int francs, fun, cost = 0, mostFun = -1, leastCost = budget+1, take;
    for (int i = 1; i <= N; ++i) {
        scanf("%i %i", &francs, &fun);

        for (int j = 5; j <= budget; ++j) {
            take = (j-francs > -1) ? dp[i-1][j-francs]+fun : 0;
            dp[i][j] = max(take, dp[i-1][j]);
            if (mostFun < dp[i][j]) {
                leastCost = j;
                mostFun = dp[i][j];
            }
            else if (j < leastCost && mostFun == dp[i][j])
                leastCost = j;
        }
    }

    printf("%i %i\n", leastCost, mostFun);
}
