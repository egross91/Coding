/*
ID: eric.bg1
LANG: C++
PROG: wormhole
*/

#include <iostream>
#include <cstdio>
#include <vector>
#include <deque>
#include <queue>
#include <stack>
#include <map>
#include <set>
#include <list>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

#define MAXL 1000000005

using namespace std;

/** DATA STRUCTURES **/
typedef pair<int,int> pii;
typedef pair<int,pair<int,int> > ppii;
typedef vector<ppii> vppii;

/** GLOBALS **/
int N, loops;
set<string> maps;

/** METHOD PROTOTYPES **/
void solve(const vppii&);
void findCycles(const vppii&, map<int,int>, int);
bool cycles(const vppii&, map<int,int>);
bool ok(const pii&, const pii&);
string stringify(map<int,int>);

int main(int argc, char** argv) {
    freopen("wormhole.in", "r", stdin);
    freopen("wormhole.out", "w", stdout);
    while (scanf("%i", &N) == 1) {
        vppii G(N);
        for (int i = 0; i < N; ++i) {
            G[i].first = i;
            scanf("%i %i", &G[i].second.first, &G[i].second.second);
        }

        solve(G);
        printf("%i\n", loops);
    }

    fclose(stdout);
    fclose(stdin);
    return 0;
}

void solve(const vppii& G) {
    map<int,int> holes;
    loops = 0;
    findCycles(G, holes, 0);
}

void findCycles(const vppii& G, map<int,int> holes, int bitmask) {
    if (bitmask == ((1 << N)-1)) {
        string mask = stringify(holes);
        if (cycles(G, holes) && maps.insert(mask).second)
            ++loops;
    }
    else {
        int i;
        for (i = 0; i < N; ++i)
            // Get first possible position for mapping.
            if (!((1 << i) & bitmask))
                break;

        for (int j = 0; j < N; ++j) {
            if (j == i || ((1 << j) & bitmask))
                continue;

            int newBitmask = bitmask | (1 << i) | (1 << j);
            map<int,int> newMap = holes;
            newMap[i] = j;
            newMap[j] = i;

            // Recursively build sets.
            findCycles(G, newMap, newBitmask);
        }
    }
}

bool cycles(const vppii& G, map<int,int> holes) {
    for (int i = 0; i < N; ++i) {
        queue<ppii> Q;
        ppii curr = G[i];
        bool first = true;
        map<int,int> counts;
        counts[curr.first]++;

        Q.push(curr);
        while (!Q.empty()) {
            ppii curr = Q.front(); Q.pop();
            int idx = -1, maxX = MAXL;

            for (int j = 0; j < N; ++j) {
                if (j == i && !first)
                    continue;
                ppii check = G[j];

                if (ok(curr.second, check.second) && check.second.first < maxX) {
                    idx = j;
                    maxX = check.second.first;
                }
            }

            if (idx != -1) {
                if (counts[G[idx].first]++ > 1)
                    return true;
                if (idx == i)
                    first = false;

                Q.push(G[holes[G[idx].first]]);
            }
        }
    }

    return false;
}

bool ok(const pii& l, const pii& r) {
     return (l.second == r.second && r.first > l.first);
}

string stringify(map<int,int> m) {
    ostringstream out;
    for (map<int,int>::iterator itr = m.begin(); itr != m.end(); ++itr) 
        out << itr->first << " " << itr->second << " ";


    return out.str();
}
