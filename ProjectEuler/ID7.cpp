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

#define LIMIT 130000

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
    bool isPrime[LIMIT];
    for (int i = 0; i < LIMIT; ++i)
        isPrime[i] = true;

    for (int m = 2; m*2 < LIMIT; ++m)
        isPrime[m*2] = false;

    int count = 1;
    for (int i = 3; i < LIMIT; i += 2) {
        if (!isPrime[i])
            continue;

        if (++count == 10001) {
            std::cout << i << std::endl;
            break;
        }

        for (int m = 2; m*i < LIMIT; ++m)
            isPrime[m*i] = false;
    }

    return 0;
}
