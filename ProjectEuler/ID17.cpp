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

std::map<int, int> getSpecialLengths();
std::map<int, int> getOnesLengths();
std::map<int, int> getTensLengths();
std::map<int, int> getHundredsLengths();

int main(int argc, char** argv) {
    std::map<int, int> specials = getSpecialLengths();
    std::map<int, int> ones = getOnesLengths();
    std::map<int, int> tens = getTensLengths();
    std::map<int, int> hundreds = getHundredsLengths();

    long long total = 0;
    for (int i = 1; i < 1000; ++i) {
        int temp = i;
        bool h = false;
        if (temp > 99) {
            temp = i / 100;
            total += hundreds[temp];
            temp = i % 100;
            h = true;
        }

        if (!temp)
            continue;

        bool t = false;
        if (temp <= 19 && temp >= 10) {
            total += specials[temp];
            t = true;
        }
        else if (temp > 0 && temp < 10) {
            total += ones[temp];
            t = true;
        }
        else {
            int ten = temp / 10;
            total += tens[ten];

            int one = temp % 10;
            total += ones[one];
            t = true;
        }

        if (h && t)
            total += 3;
    }

    total += 11;

    std::cout << total << std::endl;

    return 0;
}

std::map<int, int> getSpecialLengths() {
    std::map<int, int> ret;

    ret[10] = 3;
    ret[11] = 6;
    ret[12] = 6;
    ret[13] = 8;
    ret[14] = 8;
    ret[15] = 7;
    ret[16] = 7;
    ret[17] = 9;
    ret[18] = 8;
    ret[19] = 8;

    return ret;
}

std::map<int, int> getOnesLengths() {
    std::map<int, int> ret;

    ret[1] = 3;
    ret[2] = 3;
    ret[3] = 5;
    ret[4] = 4;
    ret[5] = 4;
    ret[6] = 3;
    ret[7] = 5;
    ret[8] = 5;
    ret[9] = 4;

    return ret;
}

std::map<int, int> getTensLengths() {
    std::map<int, int> ret;

    ret[2] = 6;
    ret[3] = 6;
    ret[4] = 5;
    ret[5] = 5;
    ret[6] = 5;
    ret[7] = 7;
    ret[8] = 6;
    ret[9] = 6;

    return ret;
}

std::map<int, int> getHundredsLengths() {
    std::map<int, int> ret;

    ret[1] = 10;
    ret[2] = 10;
    ret[3] = 12;
    ret[4] = 11;
    ret[5] = 11;
    ret[6] = 10;
    ret[7] = 12;
    ret[8] = 12;
    ret[9] = 11;

    return ret;
}
