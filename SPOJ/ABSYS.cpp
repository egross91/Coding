#include <iostream>
#include <sstream>

using namespace std;

/** GLOBALS **/
int N;

/** PUBLIC METHODS **/
string solve();
string fix(const string&);
int stringToInt(const string&);
bool isNum(const char&);

int main()
{
	cin >> N;

	cout << solve();
	
	return 0;
}

string solve()
{
	ostringstream ss;
	
	cin.ignore();
	for (int i = 0; i < N; ++i) {
		string line;
		string buff;
		getline(cin,buff);
		getline(cin, line);
		
		ss << fix(line) << endl;
	}
	
	return ss.str();
}

string fix(const string& input)
{
	string left, right, ret;
	int idx;
	for (idx = 0; idx < input.length(); ++idx) {
		if (input[idx] == ' ')
			break;
		
		if (!isNum(input[idx])) { // Machula
			left.clear();
			while (input[idx] != ' ')
				++idx;
			break;
		}
		
		// otherwise
		left.push_back(input[idx]);		
	}
	
	idx += 3;
	
	for (; idx < input.length(); ++idx) {
		if (input[idx] == ' ')
			break;
			
		if (!isNum(input[idx])) { // Machula
			right.clear();
			while (input[idx] != ' ')
				++idx;
			break;
		}
		
		// otherwise
		right.push_back(input[idx]);	
	}
	
	idx += 3;
	
	for (; idx < input.length(); ++idx) {
		if (input[idx] == ' ')
			break;
		
		if (!isNum(input[idx])) { // Machula
			ret.clear();
			break;
		}
		
		// otherwise
		ret.push_back(input[idx]);	
	}
	
	ostringstream ss; 
	int l, r, re;
	if (left.empty()) {
		r = stringToInt(right);
		re = stringToInt(ret);		
		ss << (re-r) << " + " << r << " = " << ret;
	}
	else if (right.empty()) {
		l = stringToInt(left);
		re = stringToInt(ret);
		ss << l << " + " << (re-l) << " = " << ret;
	}
	
	else { // (ret.empty()
		l = stringToInt(left);
		r = stringToInt(right);
		ss << l << " + " << r << " = " << (l+r);
	}
	
	return ss.str();	
}

int stringToInt(const string& input)
{
	stringstream ss(input);
	int x;
	ss >> x;
	
	return x;
}

bool isNum(const char& letter)
{
	return (letter-'0' >= 0 && letter-'0' <= 9);
}
