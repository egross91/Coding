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

struct SortDesc {
    bool operator()(const int& lhs, const int& rhs) {
        return lhs > rhs;
    }
} sortDesc;

void process(int*);

int T, N;

int main(int argc, char** argv) {
    scanf("%i", &T);

    while (T--) {
        scanf("%i", &N);
        int* doges = new int[N+1];
        scanf("%i", &doges[1]);
        for (int i = 2; i <= N; ++i)
            scanf("%i", &doges[i]);

        sort(doges+1, doges+N+1, sortDesc);

        process(doges);
        delete [] doges;
    }

    return 0;
}

void process(int* doges) {
    int* sums = new int[N+1];
    sums[1] = doges[1];
    for (int i = 2; i <= N; ++i)
        sums[i] = sums[i-1] + doges[i];

    if ((sums[N] & 0x1)) {
        printf("SAD\n");
        delete [] sums;
        return;
    }


    int w = N;
    for (int i = 1; i <= N; ++i) {
        while (w > 0 && doges[w] < i)
            --w;

        int y = max(i, w);
        if (sums[i] > (i*(y-1) + sums[N] - sums[y])) {
            printf("SAD\n");
            delete [] sums;
            return;
        }
    }

    delete [] sums;
    printf("HAPPY\n");
}
