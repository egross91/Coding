#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#include <cmath>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <utility>

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

bool multipleOneThruTwenty(const long long&);

int main(int argc, char** argv) {
    for (long long i = 20; i <= INFINITY; ++i) {
        if (multipleOneThruTwenty(i)) {
            std::cout << i << std::endl;
            break;
        }
    }

    return 0;
}

bool multipleOneThruTwenty(const long long& value) {
    for (int i = 1; i <= 20; ++i) {
        if ((value % i) != 0)
            return false;
    }

    return true;
}
