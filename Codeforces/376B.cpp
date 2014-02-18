#include <iostream>

using namespace std;

int arr[101] = { };

int main() 
{
	int n, m;
	cin >> n >> m;
	
	for (unsigned i = 0; i != m; ++i) {
		int a, b, c;
		cin >> a >> b >> c;
		arr[a] -= c;
		arr[b] += c;
	}
	
	int ans = 0;
	for (unsigned i = 1; i != n+1; ++i) 
		if (arr[i] < 0)
			ans -= arr[i];

	cout << ans;
	return 0;
}