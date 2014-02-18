#include <iostream>

using namespace std;

int days[100] = { };

int main()
{
	int n, c;
	cin >> n >> c;
	
	for (unsigned i = 0; i != n; ++i) 
		cin >> days[i];
		
	int most = 0;
	for (unsigned i = 0; i != n-1; ++i) {
		if (days[i] <= days[i+1]) {
			continue;
		} else {
			if ((days[i] - days[i+1] - c) > most)
				most = (days[i] - days[i+1] - c);
		}		
	}
	
	cout << most;
	return 0;
}
