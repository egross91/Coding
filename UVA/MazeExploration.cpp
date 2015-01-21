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
void dfs(Graph&, int, int);
bool wannaSearch(Graph&, int, int);

int main(int argc, char** argv) {
    int T;
    string line;
    ostringstream output;
    scanf("%i", &T);
    getline(cin, line);

    while (T--) {
        Graph G;
        while (getline(cin, line) && line[0] != '_')
            G.push_back(line);

        paint(G);

        for (int r = 0; r < G.size(); ++r)
            output << G[r] << "\n";
        output << line << "\n";
    }

    cout << output.str();
    return 0;
}

void paint(Graph& G) {
    int row, col;
    row = col = 0;

    bool found = false;
    for (; row < G.size(); ++row) {
        for (col = 0; col < G[row].size(); ++col) {
            if (G[row][col] == '*') {
                dfs(G, row, col);
                return;
            }
        }
    }
}

void dfs(Graph& G, int r, int c) {
    G[r][c] = '#';

    for (int i = 0; i < 4; ++i) {
        int newR = r + dr[i];
        int newC = c + dc[i];

        if (wannaSearch(G, newR, newC))
            dfs(G, newR, newC);
    }
}

bool wannaSearch(Graph& G, int r, int c) {
    return (r > -1 && r < G.size() && c > -1 && c < G[r].size() && G[r][c] == ' ');
}
