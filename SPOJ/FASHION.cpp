#include <cstdio>
#include <sstream>
#include <algorithm>

using namespace std;

int solve();

int main()
{
	int tests;
	scanf("%d", &tests);

	ostringstream out;
	for (int t = 0; t < tests; ++t) 
		out << solve() << endl;

	printf("%s", out.str().c_str());
	
	return 0;
}

int solve()
{
	int n;
	scanf("%d", &n);

	int* boys = new int[n];
	int* girls = new int[n];

	for (int i = 0; i < n; ++i)
		scanf("%d", &boys[i]);

	for (int i = 0; i < n; ++i)
		scanf("%d", &girls[i]);

	sort(boys,  boys+n);
	sort(girls, girls+n);

	int max = 0;
	for (int i = 0; i < n; ++i)
		max += (girls[i] * boys[i]);

	delete [] boys;
	delete [] girls;

	return max;
}
