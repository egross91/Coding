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

std::string solve(const std::string&);
std::string getChars(const int[], const int&);

int main(int argc, char** argv) {
    std::string line;

    std::ostringstream ss;
    while (std::getline(std::cin, line)) {
        ss << solve(line);
    }

    std::printf("%s", ss.str().c_str());

    return 0;
}

std::string solve(const std::string& line) {
    int alphabet[52] = { 0 }; // 0 -> 51 -- A-z
    int max = 0;

    for (int i = 0; i < line.length(); ++i) {
        if (line[i] >= 65 && line[i] <= 90) {
            int index = (int)line[i] - 'A';
            alphabet[index]++;
            max = std::max(max, alphabet[index]);
        }
        else if (line[i] >= 97 && line[i] <= 122) {
            int index = (int)line[i] - 'a' + 26;
            alphabet[index]++;
            max = std::max(max, alphabet[index]);
        }
    }

    return getChars(alphabet, max);
}

std::string getChars(const int alphabet[], const int& max) {
    std::ostringstream output;

    for (int i = 0; i < 52; ++i) {
        if (alphabet[i] == max) {
            if (i <= 25) 
                output << (char)(i + 'A');
            else
                output << (char)(i % 26 + 'a');
        }
    }

    output << " " << max << "\n";

    return output.str();
}
