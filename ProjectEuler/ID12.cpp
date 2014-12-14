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

typedef std::priority_queue<Node, std::vector<Node>, Comparison> Minheap;
typedef std::vector<std::vector<Node> > Graph;

std::vector<int> getPrimes();
int countDivisors(const int&, const std::vector<int>&);

int main(int argc, char** argv) {
    std::vector<int> primes = getPrimes();

    for (int i = 1000; i < 77000000; ++i) {
        if (countDivisors(i, primes) >= 500) {
            std::cout << i << std::endl;
            break;
        }
    }

    return 0;
}

std::vector<int> getPrimes() {
    std::vector<int> primes;
    bool sieve[77000000];

    std::cout << "2" << std::endl;
    for (int i = 2; i < 77000000; ++i)
        sieve[i] = true;

    primes.push_back(2);
    for (int i = 3; i < 77000000; i += 2) {
        if (!sieve[i] || !(i & 0x1))
            continue;

        primes.push_back(i);
        for (int m = 2; m*i < 77000000; ++m)
            sieve[m*i] = false;
    }

    std::cout << "got me primes" << std::endl;
    return primes;
}

int countDivisors(const int& value, const std::vector<int>& primes) {
    std::vector<std::pair<int, int> > cardinality;

    int temp = value;
    for (int i = 0; i < primes.size() && primes[i] < value; ++i) {
        std::pair<int, int> current(i, 0);
        while (!(temp % i)) {
            current.second++;
            temp /= i;
        }

        if (current.second)
            cardinality.push_back(current);
    }

    int sum = 1;
    for (int i = 0; i < cardinality.size(); ++i)
        sum *= (cardinality[i].second+1);
}
