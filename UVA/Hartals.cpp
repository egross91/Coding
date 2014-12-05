#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
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

int solve(const int&, const int&);

int main(int argc, char** argv) {
    int tests;
    std::scanf("%d", &tests);

    std::ostringstream output;
    for (int i = 0; i < tests; ++i) {
        int days, parties;
        std::scanf("%d%d", &days, &parties);

        output << solve(days, parties) << "\n";
    }

    std::cout << output.str();

    return 0;
}

int solve(const int& days, const int& parties) {
    bool daysOff[3651] = { false };

    int interval;
    for (int p = 0; p < parties; ++p) {
        std::scanf("%d", &interval);

        interval = (interval == 1) ? 1 : --interval;

        for (int day = 0; day < days; ++day) {
            day += interval;
            int modDay = day % 7;

            if (modDay != 5 && modDay != 6 && day < 3651)
                daysOff[day] = true;
        }
    }

    int count = 0;
    for (int i = 0; i < days; ++i)
        if (daysOff[i])
            ++count;

    return count;
}
