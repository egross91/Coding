#include <cstdio>
#include <sstream>
#include <iomanip>

using namespace std;

double solve();

int main() 
{
	int tests;
	scanf("%d", &tests);

	ostringstream ss;
	for (int i = 0; i < tests; ++i)
		ss << fixed << setprecision(2) << solve() << endl;

	printf("%s", ss.str().c_str());
	return 0;
}

double solve()
{
	int n;
	scanf("%d", &n);

	double ret = 0;

	for (int i = 1; i <= n; ++i)
		ret += double(double(n) / double(i));

	return ret;
}
