#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
	unsigned n;
	cin >> n;
	
	unsigned i;
	vector<int> k(n);
	for (i = 0; i != n; ++i)
		cin >> k[i];
	
	unsigned j = n/2;
	
	sort(k.begin(), k.end());
	int p = n;
	for (i = 0; i < n/2; ++i) {
		bool flag = false;
		while (j < n) {
			flag = true;
			if (k[i]*2 <= k[j]) {
				--p;
				++j;
				break;
			}
			++j;
		}
		if (j == n)
			break;
	}
	
	cout << p;
	return 0;
}