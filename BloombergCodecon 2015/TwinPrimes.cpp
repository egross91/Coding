//Problem        : Twin Primes
//Language       : C++
//Compiled Using : g++
//Version        : GCC 4.8.2
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

#include <iostream>
#include <cstdio>
#include <string>
#include <algorithm>
#include <climits>

#define LIMIT 1005

using namespace std;

int main() {
    int K;
    scanf("%i", &K);

    bool sieve[LIMIT];
    for (int i=1; i<=LIMIT; ++i)
        sieve[i] = true;
    for (int i=2; i<=LIMIT; ++i) {
        if (!sieve[i])
            continue;

        for (int m=2; i*m<=LIMIT; ++m)
            sieve[m*i] = false;
    }

    for (int i=K; i>=5; --i) {
        if (sieve[i] && sieve[i-2]) {
            cout << i-2 << "," << i;
            break;
        }
    }

    return 0;
}
