#include <iostream>
#include <cstdio>
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

bool isPalindrome(const int&);
bool isMultiple(const int&);

int main(int argc, char** argv) {
    int max = 998001;

    for (int i = max; i >= 1; --i) {
        if (isPalindrome(i) && isMultiple(i)) {
            std::cout << i << std::endl;
            break;
        }
    }

    return 0;
}

bool isPalindrome(const int& value) {
    std::ostringstream ss;
    ss << value;
    std::string str = ss.str();

    int low = 0;
    int high = str.length()-1;
    int mid = (high + low) >> 1;
    while (low < high) {
        if (str[low++] != str[high--])
            return false;
    }

    return true;
}

bool isMultiple(const int& value) {
    for (int i = 999; i >= 100; --i) {
        int quo = (value / i);
        if (!(value % i) && (quo >= 100 && quo <= 999)) {
            std::cout << i << std::endl;
            return true;
        }
    }

    return false;
}
