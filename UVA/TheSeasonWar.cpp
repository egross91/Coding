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
const int dr[8] = { 1, 1, 0, -1, -1, -1, 0, 1 };
const int dc[8] = { 0, 1, 1, 1, 0, -1, -1, -1 };

/** METHOD PROTOTYPES **/
int process(const Graph&, int);
void DFS(const Graph&, int, int, int, vector<vector<bool> >&);

int main(int argc, char** argv) {
    int N, image = 1;
    string line;
    ostringstream output;

    while (cin >> N) {
        Graph G(N);
        for (int i = 0; i < N; ++i)
            cin >> G[i];

        output << "Image number " << image++ << " contains " << process(G, N) << " war eagles." << endl;
    }

    cout << output.str();
    return 0;
}

int process(const Graph& G, int N) {
    int graphs = 0;
    vector<vector<bool> > visited(N, vector<bool>(N, false));

    for (int r = 0; r < N; ++r) {
        for (int c = 0; c < N; ++c) {
            if (G[r][c] != '0' && !visited[r][c]) {
                DFS(G, N, r, c, visited);
                ++graphs;
            }
        }
    }

    return graphs;
}

void DFS(const Graph& G, int N, int row, int col, vector<vector<bool> >& visited) {
    visited[row][col] = true;

    for (int i = 0; i < 8; ++i) {
        int newR = row + dr[i];
        int newC = col + dc[i];

        if (newR > -1 && newR < N && newC > -1 && newC < N &&
            G[newR][newC] != '0' && !visited[newR][newC]) {
            DFS(G, N, newR, newC, visited);
        }
    }
}
