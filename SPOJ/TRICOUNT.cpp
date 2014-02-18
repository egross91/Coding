#include <iostream>

using namespace std;

int main()
{
	int T;
	cin >> T;
	
	while (T--) {
		long long N;
		cin >> N;
		cout << (long long)((N*(N+2)*((2*N)+1))/8) << endl;
	}
	
	return 0;
}