#include <cstdio>
#include <sstream>
#include <algorithm>

using namespace std;

// GLOBALS
int n;
int arr[10002];
ostringstream output;

// PUBLIC METHODS
int solve();

int main()
{
	while ((scanf("%d", &n) == 1) && n != -1)
		output << solve() << "\n";

	printf("%s", output.str().c_str());

	return 0;
}

int solve()
{
	int sum = 0;
	for (int i = 0; i < n; ++i) {
		scanf("%d", &arr[i]);

		sum += arr[i];
	}

	if (sum % n > 0)
		return -1;

	int total = 0;
	int val = (sum / n);
	for (int i = 0; i < n; ++i)
		if (arr[i] < val)
			total += (val-arr[i]);


	return total;
}
