/*
ID: eric.bg1
LANG: C++
TASK: skidesign
*/

#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <cmath>
#include <utility>
#include <sstream>
#include <fstream>
#include <iomanip>

using namespace std;

typedef vector<int> vi;
typedef pair<vi, int> pvii;
typedef vector<pvii> vecpvii;
typedef unsigned long long ull;
struct SortSums {
    bool operator() (const pvii& lhs, const pvii& rhs) {
        return (lhs.second < rhs.second);
    }
} sortSums; // End SortSums struct

int n;

void solve(vector<int>&);

int main(int argc, char** argv) {
    freopen("skidesign.in", "r", stdin);
    freopen("skidesign.out", "w", stdout);

    while (scanf("%i", &n) == 1) {
        vector<int> hills(n);

        for (int i = 0; i < n; ++i)
            scanf("%i", &hills[i]);

            solve(hills);
    }

    return 0;
}

void solve(vector<int>& hills) {
    ull sum = 0;
    int count = 0;

    while (true) {
        vecpvii diffs;
        int max = -1;
        int min = 101;
        for (int i = 0; i < n; ++i) {
            max = std::max(max, hills[i]);
            min = std::min(min, hills[i]);
        }
        if ((max-min) <= 17) break;

        for (int i = 0; i+17 <= max; ++i) {
            int lo = i;
            int hi = i+17;
            pvii currentItr;

            for (int j = 0; j < n; ++j) {
                if (hills[j] < lo) {
                    currentItr.first.push_back(hills[j]);
                }
                if (hills[j] > hi) {
                    currentItr.first.push_back(hills[j]);
                }
            }

            if ((int)currentItr.first.size() > 0) {
                int cSum = 0;
                for (int k = 0; k < currentItr.first.size(); ++k) {
                    int val;
                    if (currentItr.first[k] < lo) {
                        val = lo-currentItr.first[k];
                        cSum += (val * val);
                    }
                    else {
                        val = currentItr.first[k]-hi;
                        cSum += (val * val);
                    }
                }
                currentItr.second = cSum;

                diffs.push_back(currentItr);
            }
        }

        sort(diffs.begin(), diffs.end(), sortSums);

        pvii matters = diffs[0];
        for (int i = 0; i < matters.first.size(); ++i) {
            int current = matters.first[i];

            for (int j = 0; j < n; ++j) {
                if (hills[j] < current) {
                    hills[j] += (current-hills[j]);
                }
                if (hills[j] > current) {
                    hills[j] -= (hills[j]-current);
                }
            }
        }

        sum += matters.second;
    }

    printf("%llu\n", sum);
}
