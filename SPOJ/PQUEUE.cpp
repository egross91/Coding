#include <iostream>
#include <queue>
#include <vector>
#include <list>
#include <sstream>

using namespace std;


struct Pair {
	int num;
	bool isMine;

	Pair() : num(0), isMine(false) { };
	Pair(int _n, bool _i) : num(_n), isMine(_i) { };
};

struct Comp {
	bool operator() (const Pair& p1, const Pair& p2) {
		return p1.num > p2.num;
	}
};


typedef priority_queue<int> PQ;

int solve();

int main()
{
	ostringstream out;
	int tests;
	cin >> tests;

	for (int i = 0; i < tests; ++i) 
		out << solve() << endl;

	cout << out.str();

	return 0;
}

int solve()
{
	int n, m;
	cin >> n >> m;
	
	PQ Q;
	list<Pair> nums;

	int curr;
	for (int i = 0; i < n; ++i) {
		cin >> curr;

		Q.push(curr);

		if (i != m)
			nums.push_back(Pair(curr,false));
		else
			nums.push_back(Pair(curr,true));
	}

	int position = 0;
	bool found = false;
	while (!found) {
		Pair curr = nums.front();

		if (Q.top() > curr.num) {
			nums.push_back(curr);
			nums.pop_front();
		}
		else {
			++position;
			found = curr.isMine;
			nums.pop_front();
			Q.pop();
		}
	}

	return position;
}
