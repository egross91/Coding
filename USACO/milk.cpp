/*
ID: eric.bg1
PROG: milk
LANG: C++
*/
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

ifstream fin("milk.in");
ofstream fout("milk.out");

struct Pair
{
	int cents;
	int milk;

	Pair() : cents(0), milk(0) { }
	Pair(int _c, int _m) : cents(_c), milk(_m) { }
};

bool CompPairs(Pair p1, Pair p2)
{
	return p1.cents < p2.cents;
}

int solve();
vector<Pair> getMilkers(const int&);
int findMinimumMilk(const int&, const vector<Pair>&);

int main()
{
	int answer = solve();

	fout << answer << endl;

	return 0;
}

int solve()
{
	int N, M;
	fin >> N >> M;

	vector<Pair> milkers = getMilkers(M);
	sort(milkers.begin(), milkers.end(), CompPairs);

	return findMinimumMilk(N, milkers);
}

vector<Pair> getMilkers(const int& M)
{
	vector<Pair> ret(M);

	for (int i = 0; i < M; ++i)
	{
		Pair p;
		fin >> p.cents >> p.milk;

		ret[i] = p;
	}

	return ret;
}

int findMinimumMilk(const int& N, const vector<Pair>& milkers)
{
	int goal = N;
	int min = 0;

	for (int i = 0; i < milkers.size(); ++i)
	{
		Pair curr = milkers[i];

		if ((goal - curr.milk) > 0)
		{
			goal -= curr.milk;
			min += (curr.cents * curr.milk);		}
		else
		{
			min += (goal * curr.cents);
			break;
		}
	}

	return min;
}