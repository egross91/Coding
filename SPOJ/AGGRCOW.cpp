#include <iostream>
#include <algorithm>

using namespace std;

int n, cows;
int stalls[100001] = { };

bool place(const unsigned mid)
{
	long long last_pos = stalls[0];
	unsigned cows_placed = 1;
	for (unsigned i = 1; i != n; ++i) {
		if (stalls[i]-last_pos >= mid) {
			++cows_placed;
			if (cows_placed == cows)
				return true;
				
			last_pos = stalls[i];
		}
	}
	return false;
}

int binarySearch()
{
	int low = 0;
	int high = stalls[n-1];
	while (low < high) {
		int mid = (low+high) >> 1;
		if (place(mid))
			low = mid+1;
		else
			high = mid;
	}
	return low-1;
}

int main()
{
	int tests;
	cin >> tests;
	
	while (tests--) {
		cin >> n >> cows;
		
		for (unsigned i = 0; i != n; ++i)
			cin >> stalls[i];
			
		sort(stalls, stalls+n);
		cout << binarySearch() << endl;	
	}
	return 0;
}
