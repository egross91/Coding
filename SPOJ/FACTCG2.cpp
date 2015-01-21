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

#define LIMIT 10000000

using namespace std;

/** METHOD PROTOTYPES **/
void buildSieve();
void factorize(int);

/** GLOBALS **/
int* predecessors;

int main(int argc, char** argv) {
    int N;
    ostringstream output;
    buildSieve();

    while (scanf("%i", &N) == 1)
        factorize(N);

    return 0;
}

void buildSieve() {
    int* pr = new int[664580];
    bool* sieve = new bool[LIMIT+5];
    predecessors = new int[LIMIT+5];
    int ptr = 0;

    for (int i = 2; i <= LIMIT; ++i) {
        if (predecessors[i] == 0) {
            predecessors[i] = i;
            pr[ptr++] = i;
        }

        for (int j = 0; j < ptr && pr[j] <= predecessors[i] && i*pr[j] <= LIMIT; ++j)
            predecessors[i*pr[j]] = pr[j];
    }

    delete [] sieve;
    delete [] pr;
}

void factorize(int N) {
    printf("1");
    while (N > 1) {
        printf(" x %i", predecessors[N]);
        N /= predecessors[N];
    }

    printf("\n");
}
