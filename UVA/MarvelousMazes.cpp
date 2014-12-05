#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <utility>

#define SPACE 'b'

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

void split(const std::string&, char delim, std::vector<std::string>& elems);
std::vector<std::string> split(const std::string&, char);
std::string buildMaze(const std::vector<std::string>&);
std::string parse(const std::string&);

int main(int argc, char** argv) {
    std::string input;
    std::stringstream ss;

    while (std::getline(std::cin, input)) {
        if (!input.empty())
            ss << input << "!";
        else
            ss << "!";
    }

    std::vector<std::string> tokens = split(ss.str(), '!');
    std::printf("%s", buildMaze(tokens).c_str());

    return 0;
}

void split(const std::string& input, char delim, std::vector<std::string>& elems) {
    std::stringstream ss(input);
    std::string token;

    while (std::getline(ss, token, delim)) {
        if (!token.empty())
            elems.push_back(token);
        else
            elems.push_back("\n");
    }
}

std::vector<std::string> split(const std::string& input, char delim) {
    std::vector<std::string> elems;
    split(input, delim, elems);

    return elems;
}

std::string buildMaze(const std::vector<std::string>& tokens) {
    std::ostringstream ss;

    for (int i = 0; i < tokens.size(); ++i) {
        std::string line = tokens[i];

        ss << parse(line) << "\n";
    }

    return ss.str();
}

std::string parse(const std::string& line) {
    std::stringstream ss;

    for (int i = 0; i < line.size(); ++i) {
        char curr = line[i];

        int n = (int)curr - '0';
        int total = 0;
        while ((n >= 0 && n <= 9) && i+1 < line.size()) {
            total += n;
            curr = line[++i];
            n = (int)curr - '0';
        }

        if (curr == SPACE)
            curr = ' ';

        for (int pos = 0; pos < total; ++pos) {
            ss << curr;
        }
    }

    return ss.str();
}
