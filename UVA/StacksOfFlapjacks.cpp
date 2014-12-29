#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <map>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

using namespace std;

int convertStringToInt(const string&);
void split(const string&, char, vector<string>&);
vector<string> split(const string&, char);
string process(vector<int>&);
void flip(vector<int>&, int);

int main(int argc, char** argv) {
    string line;

    while (getline(cin, line)) {
        vector<string> tokens = split(line, ' ');
        vector<int> stack(tokens.size());

        for (int i = 0; i < tokens.size(); ++i)
            stack[i] = convertStringToInt(tokens[i]);

        cout << process(stack) << endl;
    }

    return 0;
}

int convertStringToInt(const string& str) {
    stringstream ss(str);
    int value;
    ss >> value;

    return value;
}

void split(const string& input, char delim, vector<string>& tokens) {
    stringstream ss(input);
    string token;

    while (getline(ss, token, delim))
        if (!token.empty())
            tokens.push_back(token);
}

vector<string> split(const string& input, char delim) {
    vector<string> tokens;
    split(input, delim, tokens);

    return tokens;
}

string process(vector<int>& stack) {
    // Format output
    ostringstream ans;
    for (int i = 0; i < stack.size(); ++i)
        ans << stack[i] << " ";
    ans << "\n";

    // Create sorted version to know when to terminate.
    const int N = stack.size();
    vector<int> sorted = stack;
    sort(sorted.begin(), sorted.end());

    // Pointers to the moves and where we are in the stacks.
    vector<int> flips;
    int sortedIndex, stackIndex, currentValue;
    sortedIndex = stackIndex = N-1;
    while (stack != sorted && sortedIndex >= 0) {
        currentValue = sorted[sortedIndex--];

        // The element is at the top of the stack.
        if (stack[0] == currentValue) {
            flip(stack, N-(stackIndex+1));
            flips.push_back(N-stackIndex);
            --stackIndex;
        }
        else { // The element is within the array.
            int index = stackIndex;
            for (; index > 0; --index) {
                if (stack[index] == currentValue)
                    break;
            }

            if (index == stackIndex) {
                --stackIndex;
                continue;
            }

            flip(stack, N-(index+1));
            flips.push_back(N-index);

            if (sorted == stack) break;

            flip(stack, N-(stackIndex+1));
            flips.push_back(N-stackIndex);
            --stackIndex;
        }
    }

    // Format output
    for (int i = 0; i < flips.size(); ++i)
        ans << flips[i] << " ";
    ans << "0";

    return ans.str();
}

void flip(vector<int>& stack, int index) {
    reverse(stack.begin(), stack.end()-index);
}
