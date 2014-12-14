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

int main(int argc, char** argv) {
    unsigned a, b;

    std::ostringstream ss;
    while (std::scanf("%u%u", &a, &b) == 2) {
        unsigned ans = a ^ b;

        ss << ans << std::endl;
    }

    std::printf("%s", ss.str().c_str());

    return 0;
}
