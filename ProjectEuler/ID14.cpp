#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <cmath>
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

int collatz(long long);

int main(int argc, char** argv) {
    int max = 0;

    int value = -1;
    int previous = max;
    for (int i = 1; i < 1000000; ++i) {
        max = std::max(max, collatz(i));

        if (max > previous) {
            value = i;
            previous = max;
        }
    }

    std::cout << value << std::endl;

    return 0;
}

int collatz(long long value) {
    int count = 0;
    while (value != 1) {
        if (value & 0x1)
            value = (3 * value) + 1;
        else
            value /= 2;

        ++count;
    }

    return count;
}
