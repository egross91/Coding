#include <iostream>
#include <vector>

using namespace std;

int josephus(const int n, const int k)
{
	int r = 0;
	int i = 1;
	while (i <= n) 
		r = (r + k) % i++;
		
	return r+1;
}

int main()
{
	int n, d;
	cin >> n >> d;
	
	while (n != 0 && d != 0) {
		cout << n << " " << d << " " << josephus(n, d) << endl;
		cin >> n >> d;
	}
	return 0;
}