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

struct SortLangs {
    bool operator()(const pair<char, int>& lhs, const pair<char, int>& rhs) {
        return (lhs.second > rhs.second || (lhs.second == rhs.second && lhs.first < rhs.first));
    }
} SortLangs;

typedef vector<string> Graph;
const int dr[4] = { -1, 1, 0, 0 };
const int dc[4] = { 0, 0, -1, 1 };
int H, W;

void rank(Graph&);
void dfs(Graph&, int, int, const char);
bool wannaSearch(const Graph&, int, int, const char);

int main(int argc, char** argv) {
    int N;
    string line;

    cin >> N;
    for (int world = 1; world <= N; ++world) {
        cin >> H >> W;

        Graph G(H);
        getline(cin, line);
        for (int i = 0; i < H; ++i)
            getline(cin, G[i]);

        cout << "World #" << world << "\n";
        rank(G);
    }

    return 0;
}

void rank(Graph& G) {
    map<char, int> langMap;

    for (int r = 0; r < H; ++r) {
        for (int c = 0; c < W; ++c) {
            if (G[r][c] != '.') {
                langMap[G[r][c]]++;
                dfs(G, r, c, G[r][c]);
            }
        }
    }

    vector<pair<char, int> > langs;
    for (map<char, int>::iterator itr = langMap.begin(); itr != langMap.end(); ++itr)
        langs.push_back(pair<char, int>(itr->first, itr->second));

    sort(langs.begin(), langs.end(), SortLangs);

    for (int i = 0; i < langs.size(); ++i)
        cout << langs[i].first << ": " << langs[i].second << endl;
}

void dfs(Graph& G, int row, int col, const char lang) {
    G[row][col] = '.';

    for (int i = 0; i < 4; ++i) {
        int newR = row + dr[i];
        int newC = col + dc[i];

        if (wannaSearch(G, newR, newC, lang))
            dfs(G, newR, newC, lang);
    }
}

bool wannaSearch(const Graph& G, int r, int c, const char lang) {
    return (r > -1 && r < H && c > -1 && c < W && G[r][c] == lang);
}
