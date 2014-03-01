#include <iostream>

using namespace std;

int main()
{
	unsigned n;
	cin >> n;

	int nums[n];
	for (unsigned i = 0; i != n; ++i)
		cin >> nums[i];

	int sereja, dima;
	sereja = dima = 0;
	
	int low = 0;
	int high = n-1;
	while (low <= high) {
		// Sereja's turn
		if (nums[low] > nums[high]) 
			sereja += nums[low++];
		else
			sereja += nums[high--];
		if (low > high)
			break;
		// Dima's turn
		if (nums[low] > nums[high])
			dima += nums[low++];
		else
			dima += nums[high--];
	}
	cout << sereja << " " << dima;	
	return 0;
}
