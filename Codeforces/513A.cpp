#include <iostream>
#include <cstdio>
#include <vector>
#include <deque>
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

int n1, n2, k1, k2;

void solve();

int main(int argc, char** argv) {
    // freopen("513A.in", "r", stdin);
    while (scanf("%i %i %i %i", &n1, &n2, &k1, &k2) == 4)
        solve();

    return 0;
}

void solve() {
    while (n1 && n2) {
        n1--;
        n2--;
    }

    if (!n1)
        printf("Second\n");
    else
        printf("First\n");
}
