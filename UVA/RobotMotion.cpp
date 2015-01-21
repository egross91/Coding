#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <map>
#include <list>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

using namespace std;

/** DATA STRUCTURES **/
typedef vector<string> Graph;

/** METHOD PROTOTYPES **/
string process(const Graph&, int, int, int);
void move(const char&, pair<int, int>&);

int main(int argc, char** argv) {
    int M, N, row;

    ostringstream output;
    while ((cin >> M >> N >> row) && (M || N || row)) {
        Graph G(M);
        for (int i = 0; i < M; ++i)
            cin >> G[i];

        output << process(G, M, N, row) << endl;
    }

    cout << output.str();

    return 0;
}

string process(const Graph& G, int M, int N, int start) {
    pair<int, int> coords(0, start-1);
    vector<vector<bool> > visited(M, vector<bool>(N, false));
    int x, y, moves = 0;
    bool cycle = false;

    while (coords.first >= 0 && coords.first < M &&
           coords.second >= 0 && coords.second < N) {
        y = coords.first; x = coords.second;

        if (!visited[y][x]) {
            move(G[y][x], coords);
            visited[y][x] = true;
            ++moves;
        }
        else {
            cycle = true;
            break;
        }
    }

    ostringstream output;
    if (!cycle)
        output << moves << " step(s) to exit";
    else {
        int loop = 0;
        pair<int, int> start = coords;
        y = start.first; x = start.second;
        move(G[y][x], coords);
        ++loop;

        while (start != coords) {
            y = coords.first; x = coords.second;
            move(G[y][x], coords);
            ++loop;
        }

        output << (moves - loop) << " step(s) before a loop of " << loop << " step(s)";
    }

    return output.str();
}

void move(const char& spot, pair<int, int>& coords) {
    switch (spot) {
        case 'N':
            coords.first--;
            break;
        case 'E':
            coords.second++;
            break;
        case 'S':
            coords.first++;
            break;
        case 'W':
            coords.second--;
            break;
        default:
            break;
    }
}
