#include <iostream>
#include <algorithm>

using namespace std;

int arr[100];

int main()
{
	int n;
	cin >> n;
	
	for (unsigned i = 0; i != n; ++i)
		cin >> arr[i];
		
		
	bool flag = true;
	while (flag) {
		flag = false;
		sort(arr, arr+n);
		
		int j = n-1;
		int i = n-2;
		for (; i >= 0; --i) {
			if (arr[j] - arr[i] > 0) {
				arr[j] = (arr[j--] - arr[i]);
				flag = true;
			}
		}
	}
	int sum = 0;
	for (unsigned i = 0; i < n; ++i)
		sum += arr[i];
	
	cout << sum << endl;
	return 0;
}
