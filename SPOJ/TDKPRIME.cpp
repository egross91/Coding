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

#define LIMIT 87000000

using namespace std;

/** METHOD PROTOTYPES **/
void getPrimes();

/** GLOBALS **/
int* primes;

int main(int argc, char** argv) {
    int T, K;
    scanf("%i", &T);
    getPrimes();

    while (T--) {
        scanf("%i", &K);

        printf("%i\n", primes[K]);
    }

    // delete [] primes;
    return 0;
}

void getPrimes() {
    bool* sieve = new bool[LIMIT];
    int* p = new int[LIMIT];
    primes = new int[5100000];
    int ptr = 1;

    for (int i = 2; i <= LIMIT; ++i) {
        if (p[i] == 0) {
            p[i] = primes[ptr++] = i;
        }

        for (int k = 1; k < ptr && primes[k] <= p[i] && i*primes[k] <= LIMIT; ++k) {
            p[i*primes[k]] = primes[k];
        }
    }

    // delete [] sieve;
    // delete [] p;
}
