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
    int total = 0;
    const int THREE = 3;
    const int FIVE = 5;

    bool used[1001] = { false };
    int value;
    for (int i = 1; i*THREE < 1000; ++i) {
        value = i*THREE;
        total += value;
        used[value] = true;
    }

    for (int i = 1; i*FIVE < 1000; ++i) {
        value = i*FIVE;
        if (!used[value])
            total += value;
    }

    std::cout << total << std::endl;

    return 0;
}
