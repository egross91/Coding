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

#define MAXN 55

using namespace std;

typedef vector<string> Graph;
const int dx[4] = { 1, 0, -1, 0 };
const int dy[4] = { 0, 1, 0, -1 };
int N, M;
pair<int, int> start;
bool ok;

void dfs(const Graph& G, int r, int c, char s, int k, bool visited[MAXN][MAXN]) {
    visited[r][c] = true;
    for (int i=0; i<4; ++i) {
        int newR = r + dy[i];
        int newC = c + dx[i];
        if ((newR > -1 && newR < N && newC > -1 && newC < M)
            && ((newR == start.first &&  newC == start.second && k >= 4)
            || (!visited[newR][newC] && G[newR][newC] == s))) {
            if (newR == start.first && newC == start.second && k >= 4) {
                ok = true;
                return;
            }

            dfs(G, newR, newC, s, k+1, visited);
        }
    }
}

void process(Graph& G) {
    ok = false;
    for (int r=0; r<N; ++r) {
        for (int c=0; c<M; ++c) {
            start.first = r;
            start.second = c;
            bool visited[MAXN][MAXN];
            for (int i=0; i<N; ++i)
                for (int k=0;k<M; ++k)
                    visited[i][k] = false;
            dfs(G, r, c, G[r][c], 1, visited);
            if (ok) {
                printf("Yes");
                return;
            }
        }
    }

    printf("No");
}

int main(int argc, char** argv) {
    scanf("%i %i", &N, &M);
    Graph G(N);
    for (int i=0; i<N; ++i)
        cin >> G[i];

    process(G);

    return 0;
}
