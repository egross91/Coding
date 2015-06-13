/*
ID: eric.bg1
LANG: C++
TASK: runround
*/
#include <iostream>
#include <cstdio>

#define MAX_LEN 9

using namespace std;

string solve(const string&);
bool isRunaround(const string&);
bool isUnique(const string&);
int strToInt(const string&);
string intToStr(int);
string reverse(string);

int main(int argc, char** argv) {
    freopen("runround.in", "r", stdin);
    freopen("runround.out", "w", stdout);

    string number;
    getline(cin, number);

    string ans = solve(number);
    printf("%s\n", ans.c_str());

    fclose(stdin);
    fclose(stdout);

    return 0;
}

string solve(const string& number) {
    bool ok = false;
    int current;
    string strCurrent = number;

    while (!ok) {
        current = strToInt(strCurrent);
        ++current;
        strCurrent = intToStr(current);

        ok = isRunaround(strCurrent);
    }

    return strCurrent;
}

bool isRunaround(const string& value) {
    if (!isUnique(value)) {
        return false;
    }

    int index = 0;
    int digit;
    bool visited[MAX_LEN] = {false};

    int length = value.length();
    for (int i = 0; i < length; ++i) {
        digit = (int)value[index] - '0';

        if (visited[index] || digit == length) {
            return false;
        }

        visited[index] = true;
        index = (index + digit) % length;
    }

    return index == 0;
}

bool isUnique(const string& value) {
    if (value.length() >= 10) {
        return false;
    }

    bool visited[10] = {false};
    int length = value.length();
    int digit;
    for (int i = 0; i < length; ++i) {
        digit = (int)value[i] - '0';
        
        if (visited[digit] || digit == 0) {
            return false;
        }

        visited[digit] = true;
    }

    return true;
}

int strToInt(const string& value) {
    int multiplier = 1;
    int val = 0;
    const int length = value.length()-1;

    for (int i = length; i >= 0; --i) {
        val += (multiplier * ((int)value[i] - '0'));
        multiplier *= 10;
    }

    return val;
}

string intToStr(int value) {
    char digit;
    string result;

    while (value > 0) {
        digit = (char)(value % 10) + '0';
        result.push_back(digit);

        value /= 10;
    }

    return reverse(result);
}

string reverse(string str) {
    int lo = 0, hi = str.length()-1;
    char c;

    while (lo < hi) {
        c = str[lo];
        str[lo++] = str[hi];
        str[hi--] = c;
    }

    return str;
}