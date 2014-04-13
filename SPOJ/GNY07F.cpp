#include <iostream>
#include <sstream>
#include <stack>
#include <cmath>

using namespace std;

ostringstream output;

int solve();

int max(const int& a, const int& b)
{
	return ((a > b) ? a : b);
}

int main()
{
	int tests;
	cin >> tests;

	cin.ignore();
	for (int t = 1; t <= tests; ++t)
		output << t << " " << solve() << "\n";

	cout << output.str();

	return 0;
}

int solve()
{
	string line;

	getline(cin, line);

	stack<int> S;
	int count = 0;
	int max_count = 0;
	for (int i = 0; i < line.length(); ++i) {
		if (line[i] == '['){
			S.push(i);
			max_count = max(max_count, ++count);
		}
		else {
			S.pop();
			--count;
		}
	}

	return pow(2, max_count);
}
