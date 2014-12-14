#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int LIS(const vector<int>&);
unsigned binarySearch(const vector<int>&, int, int, const int&);

int main() {
    int N;
    scanf("%i", &N);

    vector<int> D(N);
    for (int i = 0; i < N; ++i)
        scanf("%i", &D[i]);

    printf("%i\n", LIS(D));

    return 0;
}

int LIS(const vector<int>& D) {
    int N = D.size();

    vector<int> L(N, 0);
    int length = 0;
    L[length++] = D[0];
    for (int i = 1; i < N; ++i) {
        if (L[0] > D[i]) { // first element changes
            L[0] = D[i];
        }
        else if (L[length-1] < D[i]) { // append to end of list
            L[length++] = D[i];
        }
        else { // replace the element that no longer belongs
            L[binarySearch(L, -1, length-1, D[i])] = D[i];
        }
    }

    return length;
}

unsigned binarySearch(const vector<int>& L, int low, int high, const int& value) {
    unsigned mid;

    while ((high - low) > 1) {
        mid = low + ((high - low) >> 1);

        if (L[mid] >= value) {
            high = mid;
        }
        else { // (L[mid] < value)
            low = mid;
        }
    }

    return high;
}
