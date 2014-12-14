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

typedef std::priority_queue<Node, std::vector<Node>, Comparison> Minheap;
typedef std::vector<std::vector<Node> > WeightedGraph;

void getPossibilities(const int[5]);

long long dp[100001] = { 0 };

int main(int argc, char** argv) {
    int points[5] = { 2, 3, 6, 7, 8 };
    getPossibilities(points);

    int N;
    scanf("%i", &N);

    while (N--) {
        int p;
        scanf("%i", &p);
        cout << dp[p] << endl;
    }

    return 0;
}

void getPossibilities(const int points[5]) {
    dp[0] = 1;

    for (int i = 0; i < 5; ++i) {
        int point = points[i];
        for (int j = 0; j+point <= 100000; ++j) {
            dp[j+point] += dp[j];
        }
    }
}
