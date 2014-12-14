#include <cmath>
#include <cstdio>
#include <sstream>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

vector<int> parseInput();
vector<string> split(const string&, const char&);
void split(const string&, const char&, vector<string>&);
int solve(const vector<int>&);

int main() {
    int value;
    char delim;

    vector<int> coins = parseInput();

    cout << solve(coins) << endl;

    return 0;
}

vector<int> parseInput() {
    string line;
    getline(cin, line);
    vector<string> tokens = split(line, ',');

    vector<int> values(tokens.size());
    for (int i = 0; i < tokens.size(); ++i) {
        stringstream ss;
        ss << tokens[i];
        ss >> values[i];
    }

    return values;
}

vector<string> split(const string& input, const char& delim) {
    vector<string> elements;

    split(input, delim, elements);

    return elements;
}

void split(const string& input, const char& delim, vector<string>& elements) {
    string token;
    stringstream ss(input);

    while (getline(ss, token, delim))
        if (!token.empty())
            elements.push_back(token);
}

int solve(const vector<int>& coins) {
    int N;
    scanf("%i", &N);

    vector<int> dp(N+1, 0);
    dp[0] = 1;

    for (int i = 0; i < coins.size(); ++i) {
        int coin = coins[i];
        for (int j = 0; j+coin <= N; ++j) {
            dp[j+coin] += dp[j];
        }
    }

    return dp[N];
}
