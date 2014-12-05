#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <algorithm>
#include <utility>
#include <sstream>
#include <fstream>
#include <iomanip>

struct Node {
    int vertex;
    int weight;

    Node() : vertex(0), weight(0) { };
    Node(int _v, int _w) : vertex(_v), weight(_w) { };
};

struct Comparison {
    bool operator()(const Node& n1, const Node& n2) {
        return (n1.weight < n2.weight);
    }
};

bool* getSieve();
int getNextPrime(const int&, bool* const);
void solve(const int&, bool* const);

int max = 1000001;

int main(int argc, char** argv) {
    int value;

    bool* sieve = getSieve();
    while (std::scanf("%d", &value) == 1 && value) {
        solve(value, sieve);
    }

    delete[] sieve;
    return 0;
}

bool* getSieve() {
    bool* sieve = new bool[max];
    for (int i = 2; i <= max; ++i)
        sieve[i] = true;

    for (int m = 2; m*2 <= max; ++m)
        sieve[m*2] = false;

    for (int i = 3; i <= max; i += 2) {
        if (!sieve[i])
            continue;

        for (int m = 2; m*i <= max; ++m)
            sieve[i*m] = false;
    }

    return sieve;
}

int getNextPrime(const int& prime, bool* const sieve) {
    for (int i = prime+1; i <= max; ++i) {
        if (sieve[i])
            return i;
    }
}

void solve(const int& value, bool* const sieve) {
    int a = 2;
    int b;
    int mid = value >> 1;

    while (true) {
        b = value - a;

        if (sieve[b]) {
            std::printf("%d = %d + %d\n", value, a, b);
            return;
        }
        else if (a > mid)
            break;

        a = getNextPrime(a, sieve);
    }

    std::printf("Goldbach's conjecture is wrong.");
}
