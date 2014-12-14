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

void process(int[2005]);

int main(int argc, char** argv) {
    int values[2005] = { 0 };
    process(values);
    int tests; std::scanf("%i", &tests);
    int value;

    std::ostringstream ss;
    while (tests--) {
        std::scanf("%i", &value);
        ss << values[value+1000] << std::endl;
    }

    std::printf("%s", ss.str().c_str());

    return 0;
}

void process(int values[2005]) {
    for (int i = -1000; i <= 1000; ++i) {
        long long ret = ((((((i * 567) / 9) + 7492) * 235) / 47) - 498);

        int tens = ((ret / 10) % 10);

        values[i+1000] = (tens < 0) ? -tens : tens;
    }
}
