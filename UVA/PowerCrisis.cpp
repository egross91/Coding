#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <utility>
#include <sstream>
#include <fstream>
#include <iomanip>

#define Wellington 12

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

int solve(int);
int countFalse(const bool[100], const int&, const int&, const int&);

int main(int argc, char** argv) {
    int N;

    while (std::scanf("%i", &N) == 1 && N) {
        std::printf("%i\n", solve(N));
    }

    return 0;
}

int solve(int num) {
    int m = 0;

    while (m < num) {
        int count = 1;
        int current = 0;
        ++m;
        bool regions[100] = {false};

        regions[0] = true;
        while (count < num) {
            int skip = countFalse(regions, current, num, m);
            current = (current + m + skip) % num;

            if (!regions[current]) {
                ++count;

                regions[current] = true;
                if (current == 12 && count == num)
                    return m;
                else if (current == 12)
                    break;
            }
        }
    }
}

int countFalse(const bool regions[100], const int& curr, const int& n, const int& interval) {
    int skips = 0;
    int index = curr;
    int count = 0;

    do {
        index = (index + 1) % n;
        if (!regions[index])
            ++count;
        else
            ++skips;

    } while (count < interval);

    return skips;
}
