#include <cstdio>
#include <sstream>
#include <cmath>

using namespace std;

/** GLOBALS **/
ostringstream output;

/** PUBLIC METHODS **/
void solve();
int gcd(int, int);

int main()
{
	int tests;
	scanf("%d", &tests);
	for (int t = 0; t < tests; ++t) 
		solve();
	
	printf("%s", output.str().c_str());
	
	return 0;
}

void solve()
{
	int a, b;
	scanf("%d %d", &a, &b);
	
	int N = gcd(a,b);
	int sq = sqrt(N);
	
	int count = 0;
	for (int i = 1; i <= sq; ++i) {
		if (!(N % i)) {
			count += 2;
			if (i == N/i)
				--count;
		}
	}
	
	output << count << "\n";
}

int gcd(int a, int b)
{
	if (!b)
		return a;
	else
		return gcd(b, a%b);
}
