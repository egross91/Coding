#include <iostream>
#include <cstdio>
#include <limits>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#include <cmath>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <utility>

using namespace std;

/** DATA STRUCTURES **/
struct Node {
    int vertex;
    int weight;

    Node() : vertex(0), weight(0) { };
    Node(int _v, int _w) : vertex(_v), weight(_w) { };
}; // End Node struct

struct Comparison {
    bool operator()(const Node& n1, const Node& n2) {
        return (n1.weight < n2.weight);
    }
}; // End Comparison struct

typedef priority_queue<Node, vector<Node>, Comparison> Minheap;
typedef vector<vector<Node> > WeightedGraph;

void process();

int counts[41] = { 0 };
vector<int> primes;

int main(int argc, char** argv) {
    int T;
    scanf("%i", &T);

    process();

    while (T--) {
        int N;
        scanf("%i", &N);
        cout << counts[N] << endl;
    }

    return 0;
}

void process() {
    int dp[41] = { 0 };
    dp[0] = dp[1] = dp[2] = dp[3] = 1;

    // pre-process all combinations
    for (int i = 4; i <= 40; ++i) {
        dp[i] = dp[i-1] + dp[i-4];
    }

    // build sieve
    bool* sieve = new bool[220000];
    for (int i = 2; i < 220000; ++i)
        sieve[i] = true;

    sieve[0] = sieve[1] = false;
    primes.push_back(2);
    for (int i = 3; i < 220000; ++i) {
        if (!sieve[i] || !(i & 0x1))
            continue;

        primes.push_back(i);
        for (int m = 2; i*m < 220000; ++m)
            sieve[m*i] = false;
    }

    delete[] sieve;

    // get the prime count for number of combinations
    counts[0] = counts[1] = counts[2] = counts[3] = 0;
    for (int i = 4; i <= 40; ++i) {
        int value = dp[i];

        int ans = 0;
        for (int j = 0; primes[j] <= value; ++j)
            ++ans;

        counts[i] = ans;
    }
}
