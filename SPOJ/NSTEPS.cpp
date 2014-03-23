#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

string steps(const int&, const int&);

int main()
{
	int tests;
	cin >> tests;
	
	ostringstream ss;
	for (int t = 0; t < tests; ++t) {
		int x, y;
		cin >> x >> y;
		
		ss << steps(x,y) << endl;
	}
	
	cout << ss.str();
	
	return 0;
}

string steps(const int& x, const int& y)
{
	ostringstream ss;
	if (x == y && (!(x & 0x1) && !(y & 0x1))) 
		ss << (x + x);
	else if (x == y)
		ss << ((x + y) - 1);
	else if (((x-y) == 2) && (!(x & 0x1) && !(y & 0x1)))
		ss << (x + y);
	else if ((x-y) == 2)
		ss << ((x + y) - 1);
	else
		ss << "No Number";
		
	return ss.str();
}
