#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
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

long long solve(const long long&, const long long&);

int main(int argc, char** argv) {
    long long A, L;

    int test = 1;
    while (std::scanf("%lld%lld", &A, &L) == 2 && !(A < 0 && L < 0)) {
        long long ans = solve(A, L);

        std::printf("Case %d: A = %lld, limit = %lld, number of terms = %lld\n", test++, A, L, ans);
    }

    return 0;
}

long long solve(const long long& A, const long long& L) {
    long long count = 0;

    long long value = A;
    while (value <= L) {
        ++count;

        if (value == 1) break;

        if (!(value & 0x1))
            value /= 2;
        else
            value = (3 * value) + 1;
    }

    return count;
}
