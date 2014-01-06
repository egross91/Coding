#include <iostream>

using namespace std;

int main() 
{
	int t;
	cin >> t;
	
	while (t--) {
		int n;
		cin >> n;
		
		unsigned ans = 0;
		while (n) {
			n /= 5;
			ans += n;
		}
		cout << ans << endl;
	}
	return 0;
}