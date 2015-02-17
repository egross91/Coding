#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int T;
    scanf("%i", &T);

    while (T--) {
        int N, K;
        scanf("%i%i", &N, &K);

        int count = 0, curr;
        for (int i=0; i<N; ++i) {
            scanf("%i", &curr);
            if (curr <= 0)
                ++count;
        }

        printf((count >= K) ? "NO\n" : "YES\n");
    }

    return 0;
}
