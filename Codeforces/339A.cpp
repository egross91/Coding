#include <iostream>
#include <algorithm>
#include <sstream>

using namespace std;

int arr[55];

string convert(const int& p)
{
	stringstream ss;
	ss << p;
	return ss.str();
}

int main()
{
	string s;
	cin >> s;
	
	if (s.size() == 1) {
		cout << s;
		return 0;
	}
	
	unsigned j = 0;
	for (unsigned i = 0; i < s.size(); i += 2) 
		arr[j++] = s[i]-'0';
	
	unsigned n = s.size()/2 + 1;
	sort(arr, arr+n);
	
	string ret;
	for (unsigned i = 0; i != n; ++i) {
		ret.append(convert(arr[i]));
		if (i < n-1) 
			ret.append("+");
	}
	
	cout << ret;
	return 0;
}