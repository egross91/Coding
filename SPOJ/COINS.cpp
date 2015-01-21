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

unsigned long long process(int, unsigned long long);

map<int, unsigned long long> memo;

int main(int argc, char** argv) {
    int N;
    memo[0] = 0;
    memo[1] = 1;
    memo[2] = 2;
    memo[3] = 3;

    while (scanf("%i", &N) == 1)
        printf("%llu\n", process(N, 0));

    return 0;
}

unsigned long long process(int N, unsigned long long sum) {
    if (memo[N] != 0 || !N)
        return memo[N];

    unsigned long long current = memo[N];
    current += process((N >> 2), sum + (N >> 2));
    current += process(N/3, sum + N/3);
    current += process((N >> 1), sum + (N >> 1));

    memo[N] = max((unsigned long long)N, current);
    return memo[N];
}
