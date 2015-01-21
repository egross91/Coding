#include <iostream>
#include <cstdio>
#include <unordered_set>
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
void getPredecessors();
int process(); // A, B, K
int primacity(int);

/** GLOBALS **/
int* predecessors;
int A, B, K;
ifstream in("homework.in.txt", ifstream::in);
ofstream out("homework.out.txt", ofstream::out);

int main(int argc, char** argv) {
    int T, caze = 1;
    // scanf("%i", &T);
    in >> T;
    getPredecessors();

    while (T--) {
        // scanf("%i %i %i", &A, &B, &K);
        in >> A >> B >> K;

        // printf("Case #%i: %i\n", caze++, process());
        out << "Case #" << caze++ << ": " << process() << endl;
    }

    delete [] predecessors;
    return 0;
}

void getPredecessors() {
    int* primes = new int[LIMIT+5];
    bool* sieve = new bool[LIMIT+5];
    predecessors = new int[LIMIT+5];
    int ptr = 0;
    for (int i = 2; i <= LIMIT; ++i) {
        if (predecessors[i] == 0) {
            predecessors[i] = i;
            primes[ptr++] = i;
        }

        for (int j = 0; j < ptr && primes[j] <= predecessors[i] && i*primes[j] <= LIMIT; ++j)
            predecessors[i*primes[j]] = primes[j];
    }

    delete [] sieve;
    delete [] primes;
}

int process() {
    int count = 0;
    for (int i = A; i <= B; ++i)
        if (primacity(i) == K)
            ++count;

    return count;
}

int primacity(int v) {
    unordered_set<int> pr;
    while (v > 1) {
        pr.insert(predecessors[v]);
        v /= predecessors[v];
    }

    return pr.size();
}
