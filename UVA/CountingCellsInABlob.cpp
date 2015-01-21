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
const int dr[8] = { -1, -1, -1, 0, 1, 1, 1, 0 };
const int dc[8] = { -1, 0, 1, 1, 1, 0, -1, -1 };

int largestBlob(Graph&);
void dfs(Graph&, int, int);
bool wannaSearch(Graph&, int, int);

int H, W, cells;

int main(int argc, char** argv) {
    int T;
    string line;

    cin >> T;
    getline(cin, line); // dangling line
    getline(cin, line); // empty line
    while (T--) {
        Graph G;
        while (getline(cin, line) && !line.empty())
            G.push_back(line);

        cout << largestBlob(G) << "\n";
        if (T)
            cout << "\n";
    }

    return 0;
}

int largestBlob(Graph& G) {
    H = G.size();
    W = G[0].size();

    int largest = 0;
    for (int r = 0; r < H; ++r) {
        for (int c = 0; c < W; ++c) {
            if (G[r][c] == '1') {
                cells = 1;
                dfs(G, r, c);
                largest = max(largest, cells);
            }
        }
    }

    return largest;
}

void dfs(Graph& G, int r, int c) {
    G[r][c] = '0';

    for (int i = 0; i < 8; ++i) {
        int newR = r + dr[i];
        int newC = c + dc[i];

        if (wannaSearch(G, newR, newC)) {
            ++cells;
            dfs(G, newR, newC);
        }
    }
}

bool wannaSearch(Graph& G, int r, int c) {
    return (r > -1 && r < H && c > -1 && c < W && G[r][c] == '1');
}
