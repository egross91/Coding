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
    int M, N;

    std::ostringstream ss;
    try {
        while (std::scanf("%d%d", &M, &N) == 2) {
            ss << ((M*N)-1) << std::endl;
        }
    } catch (std::exception e) {
        std::cerr << e.what() << std::endl;
    }

    std::printf("%s", ss.str().c_str());

    return 0;
}
