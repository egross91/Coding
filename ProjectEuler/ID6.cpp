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

int main(int argc, char** argv) {
    int sqrdSum = 0;
    int sum = 0;

    for (int i = 1; i <= 100; ++i) {
        sqrdSum += std::pow(i, 2);
        sum += i;
    }

    int result = std::pow(sum, 2);

    std::cout << (result - sqrdSum) << std::endl;

    return 0;
}
