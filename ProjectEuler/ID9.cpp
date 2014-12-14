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

int main(int argc, char** argv) {
    for (int a = 1; a < 1000; ++a) {
        for (int b = a+1; b < 1000; ++b) {
            for (int c = b+1; c < 1000; ++c) {
                if ((a*a + b*b) == c*c) {
                    if (a + b + c == 1000) {
                        std::cout << (a*b*c) << std::endl;
                        return 0;
                    }
                }
            }
        }
    }

    return 0;
}
