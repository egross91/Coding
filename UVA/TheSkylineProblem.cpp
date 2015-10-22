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

#define LIMIT 10000

using namespace std;

struct Triplet {
	int left, height, right;

	Triplet() : left(0), height(0), right(0) { }
	Triplet(int _l, int _h, int _r) : left(_l), height(_h), right(_r) { }
};

Triplet heights[LIMIT+5];
int longestLength;

void initIntervals();
void updateHeights(const Triplet&);
void printResults();

int main(int argc, char** argv) {
    // freopen("skyline.in", "r", stdin);
    initIntervals();
	int r, h, l;

	while (scanf("%d %d %d", &l, &h, &r) == 3) {
		Triplet input(l, h, r);

		updateHeights(input);
	}

	printResults();
	printf("\n");

    return 0;
}

void initIntervals() {
	for (int i = 0; i <= LIMIT; ++i) {
		heights[i].left  = i;
		heights[i].right = i+1;
	}
}

void updateHeights(const Triplet& building) {
	int height;

	for (int i = building.left; i < building.right; ++i) {
		height = max(heights[i].height, building.height);

		heights[i].height = height;
	}

	longestLength = max(building.right, longestLength);
}

void printResults() {
	int tempInd, i = 0;
	while (heights[i].height == 0) {
		++i;
	}

	printf("%d", i);
	for (; i < longestLength; ++i) {
		Triplet current = heights[i];

		tempInd = i;
		while (heights[tempInd].height == current.height) 
			++tempInd;

		Triplet upper = heights[tempInd];
		if (current.left != upper.left) {
			printf(" %d %d", current.height, upper.left);
		}
		else {
			printf(" %d %d", current.height, upper.right);
		}

		i = tempInd-1;
	}

	printf(" 0");
}