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

string convertIntToString(int);
pair<string, string> getLeastAndMax(int);
string swap(string, int, int);

ifstream in("cooking_the_books.in.txt", ifstream::in);
ofstream out("cooking_the_books.out.txt", ostream::out);

int main(int argc, char** argv) {
    int T, N;
    ostringstream output;

    // scanf("%i", &T);
    in >> T;

    for (int test = 1; test <= T; ++test) {
        // scanf("%i", &N);
        in >> N;
        pair<string, string> perms = getLeastAndMax(N);

        output << "Case #" << test << ": " << perms.first << " " << perms.second << endl;
    }

    out << output.str();
    return 0;
}

string convertIntToString(int value) {
    stringstream ss;
    string ret;
    ss << value;
    ss >> ret;

    return ret;
}

pair<string, string> getLeastAndMax(int input) {
    pair<string, string> ret;
    vector<string> possibilities;
    string value = convertIntToString(input);
    possibilities.push_back(value);

    bool high = false, low = false;
    int indexHigh, indexLow;
    char first, secondHigh, secondLow;
    for (int i = 0; i < value.length(); ++i) {
        first = secondHigh = secondLow = value[i];
        for (int j = i+1; j < value.length(); ++j) {
            if (i == 0 && value[j] <= secondLow && value[j] != '0') {
                secondLow = value[j];
                indexLow = j;
                low = true;
            }
            else if (i != 0 && value[j] <= secondLow) {
                secondLow = value[j];
                indexLow = j;
                low = true;
            }
            if (value[j] >= secondHigh) {
                secondHigh = value[j];
                indexHigh = j;
                high = true;
            }

            if (low)
                possibilities.push_back(swap(value, i, indexLow));
            if (high)
                possibilities.push_back(swap(value, i, indexHigh));

            low = high = false;
        }
    }

    sort(possibilities.begin(), possibilities.end());
    ret.first = possibilities[0];
    ret.second = possibilities[possibilities.size()-1];

    return ret;
}

string swap(string str, int left, int right) {
    char temp = str[left];
    str[left] = str[right];
    str[right] = temp;

    return str;
}
