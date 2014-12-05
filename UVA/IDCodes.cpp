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

bool next_perm(std::string&, const int&, const int&);
void reverse(std::string&, int, int);

int main(int argc, char** argv) {
    std::string input;

    while (std::getline(std::cin, input)) {
        if (input == "#")
            break;

        if (next_perm(input, 0, input.length()))
            std::cout << input;
        else
            std::cout << "No Successor";

        std::cout << "\n";
    }

    return 0;
}

bool next_perm(std::string& str, const int& first, const int& last) {
    if ((last - first) == 1 || (last - first) == 0)
        return false;

    bool desc = true;
    for (int i = 0; i < last-1; ++i) {
        if (str[i] < str[i+1]) {
            desc = false;
            break;
        }
    }

    if (desc)
        return false;

    int i = last-1;

    while (true) {
        int ii = i;
        --i;

        if (str[i] < str[ii]) {
            int j = last;

            while (!(str[i] < str[--j])) {}

            std::swap(str[i], str[j]);
            reverse(str, ii, last-1);
            return true;
        }
        if (i == first) {
            return false;
        }
    }
}

void reverse(std::string& str, int low, int high) {
    while (low < high) {
        char temp = str[low];
        str[low++] = str[high];
        str[high--] = temp;
    }
}
