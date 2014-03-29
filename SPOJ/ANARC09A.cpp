#include <iostream>
#include <stack>
#include <sstream>

using namespace std;

/** GLOBALS **/
ostringstream output;
string input;

/** PUBLIC METHODS **/
int solve();

int main()
{
	int test = 1;
	while (cin >> input && input[0] != '-') 
		output << test++ << ". " << solve() << endl;
	
	cout << output.str();
	
	return 0;
}

int solve()
{
	stack<int> S;
	
	int missed = 0;
	
	for (int i = 0; i < input.length(); ++i) {
		if (input[i] == '{') {
			S.push(i);
			continue;
		}
		else {
			if (S.size() < 1) {
				++missed;
				continue;
			}
			S.pop();
		}
	}
	
	if (S.size() > 0 && (S.size() & 0x1))
		return ((S.size()+missed) / 2) + 1;
	else 
		return (missed+S.size()) / 2;
}
