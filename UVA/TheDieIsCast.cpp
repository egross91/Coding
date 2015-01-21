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
const int dr[4] = { 0, -1, 1, 0 };
const int dc[4] = { -1, 0, 0, 1 };

/** METHOD PROTOTYPES **/
string process(Graph&);
void dfs(Graph&, int, int);
void __dfs(Graph&, int, int);
bool canSearch(int, int);

/** GLOBALS **/
int W, H, dots;

int main(int argc, char** argv) {
    string endline;
    ostringstream output;
    int test = 1;

    while (scanf("%i %i", &W, &H) == 2 && W && H) {
        getline(cin, endline);

        Graph G(H);
        for (int i = 0; i < H; ++i)
            getline(cin, G[i]);

        output << "Throw " << test++ << "\n" << process(G) << "\n\n";
    }

    cout << output.str();
    return 0;
}

string process(Graph& G) {
    vector<int> counts;

    for (int r = 0; r < H; ++r) {
        for (int c = 0; c < W; ++c) {
            if (G[r][c] != '.') {
                dots = 0;
                dfs(G, r, c);
                counts.push_back(dots);
            }
        }
    }
    sort(counts.begin(), counts.end());

    ostringstream ret;
    if (counts.size()) {
        ret << counts[0];
        for (int i = 1; i < counts.size(); ++i)
            ret << " " << counts[i];
    }
    else
        ret << 0;

    return ret.str();
}

void dfs(Graph& G, int row, int col) {
    if (G[row][col] == 'X') {
        __dfs(G, row, col);
        ++dots;
    }
    else
        G[row][col] = '.';

    for (int i = 0; i < 4; ++i) {
        int newR = row + dr[i];
        int newC = col + dc[i];

        if (canSearch(newR, newC) && G[newR][newC] != '.')
            dfs(G, newR, newC);
    }
}

void __dfs(Graph& G, int row, int col) {
    G[row][col] = '.';

    for (int i = 0; i < 4; ++i) {
        int newR = row + dr[i];
        int newC = col + dc[i];

        if (canSearch(newR, newC) && G[newR][newC] == 'X')
            __dfs(G, newR, newC);
    }
}

bool canSearch(int r, int c) {
    return (r > -1 && r < H && c > -1 && c < W);
}
