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

typedef vector<string> Graph;
const int dr[4] = { 0, 1, 0, -1 };
const int dc[4] = { -1, 0, 1, 0 };

void paint(Graph&);
void dfs(Graph&, int, int, const char);
bool wannaSearch(const Graph&, int, int);

int main(int argc, char** argv) {
    string line;
    ostringstream output;

    while (getline(cin, line)) {
        Graph G;
        G.push_back(line);
        while (getline(cin, line)) {
            G.push_back(line);
            if (line[0] == '_')
                break;
        }

        paint(G);

        for (int r = 0; r < G.size(); ++r)
            output << G[r] << "\n";
    }

    cout << output.str();
    return 0;
}

void paint(Graph& G) {
    for (int r = 0; r < G.size()-1; ++r)
        for (int c = 0; c < G[r].size(); ++c)
            if (G[r][c] != ' ' && G[r][c] != 'X')
                dfs(G, r, c, G[r][c]);
}

void dfs(Graph& G, int r, int c, const char mark) {
    G[r][c] = mark;

    for (int i = 0; i < 4; ++i) {
        int newR = r + dr[i];
        int newC = c + dc[i];

        if (wannaSearch(G, newR, newC))
            dfs(G, newR, newC, mark);
    }
}

bool wannaSearch(const Graph& G, int r, int c) {
    return (r > -1 && r < G.size() && c > -1 && c < G[r].size() && G[r][c] == ' ');
}
