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

std::string solve(const std::vector<std::string>&);

int main(int argc, char** argv) {
    std::string line;
    std::vector<std::string> lines;

    while (std::getline(std::cin, line)) {
        lines.push_back(line);
    }

    std::cout << solve(lines);

    return 0;
}

std::string solve(const std::vector<std::string>& input) {
    std::ostringstream ss;
    for (int i = 0; i < input.size(); i += 2) {
        std::string first = input[i];
        std::string second = input[i+1];

        int m = first.length();
        int n = second.length();

        std::vector<std::vector<int> > dp(m+1, std::vector<int>(n+1, 0));
        int max = -1;

        for (int i = 1; i <= first.length(); ++i) {
            for (int j = 1; j <= second.length(); ++j) {
                if (first[i-1] == second[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = std::max(dp[i][j-1], dp[i-1][j]);
            }
        }
        // max = -1;
        //
        // for (int f1 = 0; f1 < first.length(); ++f1) {
        //     int tempS = 0;
        //     int length = 0;
        //
        //     for (int f2 = f1; f2 < first.length(); ++f2) {
        //         for (int s = tempS; s < second.length(); ++s) {
        //             if (first[f2] == second[s]) {
        //                 ++length;
        //                 tempS = s+1;
        //                 break;
        //             }
        //         }
        //     }
        //     max = std::max(max, length);
        // }
        // ss << max << std::endl;

        ss << dp[m][n] << std::endl;
    }

    return ss.str();
}
