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
    int fib[50] = { 0 };
    fib[1] = fib[2] = 1;
    long long total = 0;
    for (int i = 3; i < 50; ++i) {
        fib[i] = fib[i-1] + fib[i-2];
        if (!(fib[i] & 0x1) && fib[i] <= 4000000)
            total += fib[i];
    }

    std::cout << total << std::endl;

    return 0;
}
