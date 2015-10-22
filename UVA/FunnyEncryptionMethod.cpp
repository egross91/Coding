#include <iostream>
#include <cstdio>
#include <vector>
#include <deque>
#include <queue>
#include <stack>
#include <map>
#include <set>
#include <list>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

#define LIMIT 31

using namespace std;

pair<int, int> solve(const int);
int bitCount(int);
int hexBitCount(int);

int main(int argc, char** argv) {
    // freopen("", "r", stdin);
    
    int tests, number;
    scanf("%d", &tests);

    while (tests--) {
    	scanf("%d", &number);
    	pair<int, int> answers = solve(number);

    	printf("%d %d\n", answers.first, answers.second);
    }

    return 0;
}

pair<int, int> solve(const int number) {
	pair<int, int> results;
	results.first = bitCount(number);
	results.second = hexBitCount(number);

	return results;
}

int bitCount(int value) {
	int count = 0;
	int bitValue;

	for (int i = 0; i <= LIMIT; ++i) {
		bitValue = (1 << i);

		if ((value & bitValue)) {
			++count;
		}
	}

	return count;
}

int hexBitCount(int value) {
	int count = 0;
	int digit;

	while (value > 0) {
		digit = value % 10;

		count += bitCount(digit);

		value /= 10;
	}

	return count;
}
