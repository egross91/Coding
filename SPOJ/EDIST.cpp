#include <iostream>
#include <cstdio>
#include <cstring>
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

void process(const string&, const string&);

int main(int argc, char** argv) {
    int T;
    string word1, word2;
    scanf("%i", &T);

    while (T--) {
        cin >> word1 >> word2;

        process(word1, word2);
    }

    return 0;
}

void process(const string& word1, const string& word2) {
    const int N = word1.size();
    const int M = word2.size();

    int** dp = new int*[2005];
    for (int i = 0; i < 2005; ++i)
        dp[i] = new int[2005];

    for (int i = 0; i < N; ++i)
        dp[i][0] = i;
    for (int i = 0; i < M; ++i)
        dp[0][i] = i;

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            if (word1[i-1] == word2[j-1])
                dp[i][j] = dp[i-1][j-1];
            else
                dp[i][j] = 1 + min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]));
        }
    }

    // for (int i = 0; i < 2005; ++i)
    //     delete [] dp[i];
    // delete [] dp;

    printf("%i\n", dp[N][M]);
}
