//Problem        : SI Exam Room
//Language       : C++
//Compiled Using : g++
//Version        : GCC 4.8.2
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int N, S;

bool works(const vector<int>& s, int d, int c) {
    int placed = 1;
    int prev = s[0];
    for (int i=1; i<N; ++i) {
        if ((s[i] - prev) >= d) {
            ++placed;
            if (placed >= c)
                return true;
            prev = s[i];
        }
    }

    return false;
}

int main() {
    scanf("%i %i", &N, &S);

    vector<int> students(N);
    for (int i=0; i<N; ++i)
        scanf("%i", &students[i]);

    sort(students.begin(), students.end());

    int hi = students[N-1], lo = 1;
    int mid;
    do {
        mid = (lo + ((hi - lo) >> 1));

        if (works(students, mid, S))
            lo = mid;
        else
            hi = mid;
    } while(hi - lo > 1);

    cout << lo << endl;

    return 0;
}
