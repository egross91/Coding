#include <iostream>
#include <cstdio>
#include <vector>
#include <deque>
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

struct SortPairs {
    bool operator()(const pair<vector<int>,int>& l, const pair<vector<int>,int>& r) {
        if (l.second > r.second)
            return true;
        else if (l.second < r.second)
            return false;
        else
            return (l.first < r.first);
    }
} SortPairs;

int f(const vector<int>& perm) {
    int sum = 0;
    for (int i=0; i<(int)perm.size(); ++i) {
        int small = perm[i];
        for (int j=i; j<(int)perm.size(); ++j) {
            small = perm[i];
            for (int k=i; k<=j; ++k) {
                small = min(small, perm[k]);
            }
            sum += small;
        }
    }

    return sum;
}

int main(int argc, char** argv) {
    // freopen("", "r", stdin);
    int N, M;
    scanf("%i %i", &N, &M);
    vector<int> perms(N);
    for (int i=0; i<N; ++i)
        perms[i] = i+1;


    vector<pair<vector<int>,int> > ps;
    do {
        ps.push_back(pair<vector<int>,int>(perms, f(perms)));
    } while (next_permutation(perms.begin(), perms.end()));


    sort(ps.begin(), ps.end(), SortPairs);
    vector<int> ans = ps[M-1].first;

    // for (int i=0; i<(int)ps.size(); ++i) {
    //     cout << ps[i].second << endl;
    //     for (int j=0; j<(int)ps[i].first.size(); ++j)
    //         cout << ps[i].first[j] << " ";
    //     cout << endl << endl;
    // }

    printf("%i", ans[0]);
    for (int i=1; i<N; ++i)
        printf(" %i", ans[i]);

    return 0;
}
