/*
ID: eric.bg1
PROG: barn1
LANG: C++
*/
#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

ifstream fin("barn1.in");
ofstream fout("barn1.out");

struct Pair 
{
	int left, right;

	Pair() : left(0), right(0) { }
	Pair(int _l, int _r) : left(_l), right(_r) { }
};

bool CompPairs(Pair p1, Pair p2)
{
	return (p1.right - p1.left) > (p2.right - p2.left);
}



int solve();
vector<int> getStalls(const int&);
vector<Pair> getDiffs(const vector<int>&);
int getSumOfGaps(const int&, const vector<Pair>&);
bool allSame(const vector<Pair>&);

int main()
{
	int answer = solve();

	fout << answer << endl;

	return 0;
}

int solve()
{
	int M, S, C;
	fin >> M >> S >> C;

	vector<int> stalls = getStalls(C);
	sort(stalls.begin(), stalls.end());

	vector<Pair> diffs = getDiffs(stalls);
	sort(diffs.begin(), diffs.end(), CompPairs);

	// bool flag = allSame(diffs);

	int high = stalls[stalls.size() - 1];
	int low = stalls[0];

	int ans = 0;
	int gaps = getSumOfGaps(M, diffs);
	int range = (S - (S - high) - low);
	if (range < gaps)
		ans = (range + gaps);
	else
		ans = (range - gaps);

	return ans + 1;
}

vector<int> getStalls(const int& C)
{
	vector<int> ret(C);

	for (int i = 0; i < C; ++i)
		fin >> ret[i];

	return ret;
}

vector<Pair> getDiffs(const vector<int>& stalls)
{
	vector<Pair> ret;
	int left, right;

	for (int i = 1; i < stalls.size(); ++i)
	{
		Pair curr;

		curr.left = stalls[i-1];
		curr.right = stalls[i];

		ret.push_back(curr);
	}

	return ret;
}

int getSumOfGaps(const int& M, const vector<Pair>& diffs)
{
	int ans = 0;

	for (int i = 0; i < M-1 && i < diffs.size(); ++i)
	{
		ans += ((diffs[i].right - diffs[i].left) - 1);
	}

	return ans;
}

bool allSame(const vector<Pair>& diffs)
{
	int diff = diffs[0].right - diffs[0].left;

	for (int i = 1; i < diffs.size(); ++i)
		if ((diffs[i].right - diffs[i].left) != diff)
			return false;

	return true;
}
