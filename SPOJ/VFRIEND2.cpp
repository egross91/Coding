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
    bool operator()(const unsigned long long& lhs, const unsigned long long& rhs) {
        return lhs > rhs;
    }
} sortDesc;

/** METHOD PROTOTYPES **/
unsigned long long* buildSequence();
void process(const unsigned long long*);

/** GLOBALS **/
int N, a, b, c, m;

int main(int argc, char** argv) {
    int T;
    scanf("%i", &T);

    while (T--) {
        scanf("%i %i %i %i %i", &N, &a, &b, &c, &m);
        unsigned long long* doges = buildSequence();
        sort(doges+1, doges+N+1, sortDesc);
        process(doges);
        
        delete [] doges;
    }

    return 0;
}

unsigned long long* buildSequence() {
    unsigned long long* s = new unsigned long long[N+1];
    unsigned long long* x = new unsigned long long[N+1];

    x[1] = 0;
    s[1] = x[1] + c;
    for (int i = 2; i <= N; ++i) {
        x[i] = (unsigned long long)((unsigned long long)((unsigned long long)a*x[i-1])%m + (unsigned long long)b%m) % m;
        s[i] = x[i] + c;
    }

    delete [] x;
    return s;
}

void process(const unsigned long long* doges) {
    unsigned long long* sums = new unsigned long long[N+1];
    sums[1] = doges[1];
    for (int i = 2; i <= N; ++i)
        sums[i] = sums[i-1] + doges[i];

    if ((sums[N] & 0x1)) {
        printf("SAD\n");
        delete [] sums;
        return;
    }

    unsigned long long w = N;
    bool ok = true;
    for (unsigned long long i = 1; i <= N; ++i) {
        while (w > 0 && doges[w] < i)
            --w;

        unsigned long long y = max(w, i);
        if (sums[i] > (i*(y-1) + sums[N] - sums[y])) {
            ok = false;
            break;
        }
    }

    delete [] sums;
    (ok) ? printf("HAPPY\n") : printf("SAD\n");
}
