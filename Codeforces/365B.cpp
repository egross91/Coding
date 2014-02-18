#include <iostream>
#define MAX_SIZE 100000

using namespace std;

int arr[MAX_SIZE] = { 0 };
int N;

int max()
{
	unsigned max = 2;
	unsigned it_max = 2;
	for (unsigned i = 2; i != N; ++i) {
		if (arr[i] == (arr[i-1] + arr[i-2]))
			++it_max;
		else {
			if (it_max > max)
				max = it_max;
			it_max = 2;
		}
	}
	
	return (max > it_max) ? max : it_max;
}

int main()
{	
	cin >> N;
	if (N < 3) {
		if (N == 1)
			cout << 1;
		else 
			cout << 2;
		return 0;
	}
	
	for (unsigned i = 0; i != N; ++i)
		cin >> arr[i];

	cout << max();
	return 0;
}