#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

unsigned low_sum = 0;
unsigned up_sum = 0;

unsigned solve(const vector<int>& points)
{
	unsigned x, y;
	unsigned k = 0;
	cin >> x >> y;
	bool found = false;
	for (unsigned i = 0; i != points.size(); ++i) {
		if ((low_sum >= x && low_sum <= y) && (up_sum >= x && up_sum <= y)) {
			k = i+1;
			found = true;
			break;
		}
		low_sum += points[i];
		up_sum -= points[i];
	}
	
	if (!found) 
		return 0;
	
	return k;
}

int main()
{
	unsigned m;
	cin >> m;
	
	vector<int> points(m);
	for (unsigned i = 0; i != m; ++i) {
		cin >> points[i];
		up_sum += points[i];
	}
	
	cout << solve(points);
	return 0;
}