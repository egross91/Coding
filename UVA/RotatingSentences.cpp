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

void process(std::string[100], const int&);

int main(int argc, char** argv) {
    std::string lines[100] = { "" };
    std::string line;
    int index = 0;

    while (std::getline(std::cin, line)) {
        lines[index++] = line;
    }

    process(lines, index-1);

    return 0;
}

void process(std::string lines[100], const int& limit) {
    std::vector<std::string> ret;
    bool ok = true;
    int col = 0;

    while (ok) {
        std::ostringstream ss;
        ok = false;
        for (int s = limit; 0 <= s; --s) {
            if (col < lines[s].length()) {
                ss << lines[s][col];
                ok = true;
            }
            else
                ss << " ";
        }
        ++col;
        ret.push_back(ss.str() + "\n");
    }

    for (int i = 0; i < ret.size()-1; ++i) {
        if (!ret[i].empty())
            std::printf(ret[i].c_str());
    }
}
