#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <cmath>
#include <utility>
#include <sstream>
#include <fstream>
#include <iomanip>

struct Node {
    int vertex;
    int weight;

    Node() : vertex(0), weight(0) { };
    Node(int _v, int _w) : vertex(_v), weight(_w) { };
};

struct Comparison {
    bool operator()(const Node& n1, const Node& n2) {
        return (n1.weight < n2.weight);
    }
};

typedef std::priority_queue<Node, std::vector<Node>, Comparison> Minheap;
typedef std::vector<std::vector<Node> > Graph;

const int cents[5] = { 1, 5, 10, 25, 50 };

std::vector<int> solve();

int main(int argc, char** argv) {
    int value;

    std::vector<int> ans = solve();
    std::ostringstream ss;
    while (std::scanf("%i", &value) == 1) {
        ss << ans[value] << "\n";
    }

    std::printf("%s", ss.str().c_str());

    return 0;
}

std::vector<int> solve() {
    std::vector<int> dp(7501, 0);
    dp[0] = 1;

    for (int c = 1; c < 6; ++c) {
        int cent = cents[c-1];

        for (int j = 0; j+cent <= 7500; ++j) {
            dp[j+cent] += dp[j];
        }
    }

    return dp;
}
