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

std::string solve(const int&);
std::vector<int> getRowSums(const bool[100][100], const int&);
std::vector<int> getColSums(const bool[100][100], const int&);
std::pair<int, int> countOdds(const std::vector<int>&, const int&);

int main(int argc, char** argv) {
    int value;

    std::ostringstream ss;
    while (std::scanf("%i", &value) == 1 && value) {
        ss << solve(value);
    }

    std::printf("%s", ss.str().c_str());

    return 0;
}

std::string solve(const int& n) {
    bool matrix[100][100] = { false };
    for (int r = 0; r < n; ++r)
        for (int c = 0; c < n; ++c)
            std::scanf("%i", &matrix[r][c]);

    std::vector<int> rowSums = getRowSums(matrix, n);
    std::vector<int> colSums = getColSums(matrix, n);

    std::pair<int, int> oddRowCount = countOdds(rowSums, n);
    std::pair<int, int> oddColCount = countOdds(colSums, n);

    std::ostringstream ss;
    if (oddRowCount.first > 1 || oddColCount.first > 1)
        ss << "Corrupt\n";
    else if (!oddRowCount.first && !oddColCount.first)
        ss << "OK\n";
    else
        ss << "Change bit (" << (oddRowCount.second+1) << "," << (oddColCount.second+1) << ")\n";

    return ss.str();
}

std::vector<int> getRowSums(const bool matrix[100][100], const int& n) {
    std::vector<int> ret(n, 0);

    for (int r = 0; r < n; ++r) {
        int count = 0;
        for (int c = 0; c < n; ++c) {
            if (matrix[r][c])
                ++count;
        }

        ret[r] = count;
    }

    return ret;
}

std::vector<int> getColSums(const bool matrix[100][100], const int& n) {
    std::vector<int> ret(n, 0);

    for (int c = 0; c < n; ++c) {
        int count = 0;
        for (int r = 0; r < n; ++r) {
            if (matrix[r][c])
                ++count;
        }

        ret[c] = count;
    }

    return ret;
}

std::pair<int, int> countOdds(const std::vector<int>& sums, const int& n) {
    std::pair<int, int> ret(0, 0);

    for (int i = 0; i < n; ++i) {
        if (sums[i] & 0x1) {
            ret.first++;
            ret.second = i;
        }
    }

    return ret;
}
