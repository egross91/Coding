#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

vector<int> LCS(const vector<int>&, const int&, const vector<int>&, const int&);
void backtrack(const vector<vector<int> >&, const vector<int>&, const vector<int>&, const int&, const int&, vector<int>&);

int main() {
    int N, M;
    scanf("%i %i", &N, &M);

    vector<int> A(N+1), B(M+1);
    for (int i = 1; i <= N; ++i)
        scanf("%i", &A[i]);
    for (int i = 1; i <= M; ++i)
        scanf("%i", &B[i]);

    vector<int> ans = LCS(A, N, B, M);
    for (int i = ans.size()-1; i >= 0; --i)
        cout << ans[i] << " ";
    cout << endl;

    return 0;
}

vector<int> LCS(const vector<int>& A, const int& N, const vector<int>& B, const int& M) {
    vector<vector<int> > dp(N+1, vector<int>(M+1, 0));

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            if (A[i] == B[j])
                dp[i][j] = dp[i-1][j-1] + 1;
            else
                dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
        }
    }

    vector<int> ret;
    backtrack(dp, A, B, N, M, ret);
    return ret;
}

void backtrack(const vector<vector<int> >& dp, const vector<int>& A, const vector<int>& B,
               const int& N, const int& M, vector<int>& L) {
    if (!N || !M)
        return;
    else if (A[N] == B[M]) {
        L.push_back(A[N]);
        backtrack(dp, A, B, N-1, M-1, L);
    }
    else {
        if (dp[N][M-1] > dp[N-1][M])
            backtrack(dp, A, B, N, M-1, L);
        else
            backtrack(dp, A, B, N-1, M, L);
    }
}
