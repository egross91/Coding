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

void process(const int& N, const int& M) {
    bool right = true;
    bool flip = false;
    for (int r=0; r<N; ++r) {
        for (int c=0; c<M; ++c) {
            if (!(r & 0x1)) {
                printf("#");
                if (!flip) {
                    right = !right;
                    flip = true;
                }
            }
            else {
                if (c != 0 && c != M-1) {
                    printf(".");
                }
                else if (!c && right) {
                    printf("#");
                }
                else if (c == M-1 && !right) {
                    printf("#");
                }
                else {
                    printf(".");
                }
            }
        }
        flip = false;
        printf("\n");
    }
}

int main(int argc, char** argv) {
    int M, N;

    scanf("%i %i", &N, &M);

    process(N, M);

    return 0;
}
