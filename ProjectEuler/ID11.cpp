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
    int values[20][20];
    std::ifstream in("input.in");

    int total = -1;
    for (int r = 0; r < 20; ++r) {
        for (int c = 0; c < 20; ++c) {
            in >> values[r][c];
            // std::cout << values[r][c] << " ";
        }
        // std::cout << std::endl;
    }

    int max = -1;
    for (int r = 0; r < 20; ++r) {
        for (int c = 0; c < 20; ++c) {
            // Check right.
            if (c+3 < 20)
                max = std::max(max, values[r][c] * values[r][c+1] * values[r][c+2] * values[r][c+3]);
            // Check left.
            if (c-3 > 0)
                max = std::max(max, values[r][c-3] * values[r][c-2] * values[r][c-1] * values[r][c]);
            // Check down.
            if (r+3 < 20)
                max = std::max(max, values[r][c] * values[r+1][c] * values[r+2][c] * values[r+3][c]);
            // Check up.
            if (r-3 > 0)
                max = std::max(max, values[r-3][c] * values[r-2][c] * values[r-1][c] * values[r][c]);
            // Check left-up diag.
            if (r-3 > 0 && c-3 > 0)
                max = std::max(max, values[r][c] * values[r-1][c-1] * values[r-2][c-2] * values[r-3][c-3]);
            // Check right-up diag.
            if (r-3 > 0 && c+3 < 20)
                max = std::max(max, values[r][c] * values[r-1][c+1] * values[r-2][c+2] * values[r-3][c+3]);
            // Check left-down diag.
            if (r+3 < 20 && c-3 > 0)
                max = std::max(max, values[r][c] * values[r+1][c-1] * values[r+2][c-2] * values[r+3][c-3]);
            // Check right-down diag.
            if (r+3 < 20 && c+3 < 20)
                max = std::max(max, values[r][c] * values[r+1][c+1] * values[r+2][c+2] * values[r+3][c+3]);
        }
    }

    std::cout << max << std::endl;

    return 0;
}
