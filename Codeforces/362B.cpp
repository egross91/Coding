#include <iostream>
#include <vector>

using namespace std;

int n, m;

bool check(const vector<bool>& s)
{
	if (s[1] || s[n]) 
		return false;
	
	unsigned i = 1;
	while (i != n) {
		if (i+3 <= n && !s[i+3])
			i += 3;
		else if (i+2 <= n && !s[i+2])
			i += 2;
		else if (i+1 <= n && !s[i+1])
			++i;
		else 
			return false;
	}
	return true;
}

int main()
{
	cin >> n >> m;
	
	vector<bool> s(n+1, 0);
	for (unsigned i = 0; i != m; ++i) {
		int tmp;
		cin >> tmp;
		s[tmp] = true;
	}
	
	cout << (check(s) ? "YES" : "NO");
	return 0;
}