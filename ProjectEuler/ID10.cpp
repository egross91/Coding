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

#define LIMIT 200001

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
    long long total = 0;
    bool primes[2000000];
    for (int i = 0; i < 2000000; ++i)
        primes[i] = true;

    for (int i = 2; i*2 < 2000000; ++i)
        primes[i*2] = false;

    total += 2;
    for (int i = 3; i < 2000000; i += 2) {
        if (!primes[i] || !(i & 0x1))
            continue;

        total += i;
        for (int m = 2; m*i < 2000000; ++m)
            primes[m*i] = false;
    }

    std::cout << total << std::endl;

    return 0;
}
