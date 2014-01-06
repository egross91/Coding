#include <iostream>

using namespace std;

int main() 
{
	int n, m, k;
	cin >> n >> m >> k;
	
	int ans = 0;
	for (unsigned i = 0; i != n; ++i) {
		unsigned short p;
		cin >> p;
		if (p == 1 && m > 0)
			--m;
		else if (p == 2 && k > 0)
			--k;
		else if (p == 2 && m > 0) 
			--m;
		else 
			++ans;
	}
	
	cout << ans;
	return 0;
}