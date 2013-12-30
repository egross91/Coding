#include <iostream>
#include <cmath>

using namespace std;

int main() 
{
	int n, m, k;
	cin >> n >> m >> k;
	
	unsigned ans = 0;
	for (unsigned i = 0; i != n; ++i) {
		unsigned short p;
		cin >> p;
		if (p == 1 && m)
			--m;
		else if (p == 1 && k)
			--k;
		else if (p == 2 && m)
			--m;
		else if (p == 2 && k)
			--k;
		else
			++ans;
	}
	
	cout << ans;
	return 0;
}