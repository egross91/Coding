#include <cstdio>
#include <iostream>
#include <sstream>

using namespace std;

bool isUnderscore(const char& a)
{
	return (a == '_');
}

bool upper(const char& a)
{
	return ((int)a >= 65 && (int)a <= 90);
}

bool check(const char& a, const char& b)
{
	if (isUnderscore(a) && isUnderscore(b)) 	
		return false;
		
	if (isUnderscore(a) && upper(b)) 
		return false;
	

	return true;
}

string solve(const string& input)
{
	size_t length = input.length();
	if (isUnderscore(input[0]) || isUnderscore(input[length-1])
		|| upper(input[0]))
		return "Error!";
	
	// otherwise
	ostringstream ss;
	bool cpp = false; // original string is cpp
	bool java = false;
	ss << input[0];
	for (int i = 1; i < length; ++i) {
		if (!check(input[i-1], input[i])) 
			return "Error!";
		
		
		if (upper(input[i])) { // JAVA CASE
			java = true;
			if (java && cpp)
				return "Error!";
				
			ss << "_" << char(input[i] + 32);
			continue;
		}	
		
		if (isUnderscore(input[i])) { // C++ CASE
			if (!check(input[i],input[i+1]))
				return "Error!";
			
			cpp = true;
			if (java && cpp)
				return "Error!";
			ss << char(input[++i] - 32);
			continue;
		}
		
		ss << input[i];
	}
	
	return ss.str();
}

int main()
{
	string input;
	ostringstream ss;
	while (cin >> input) 
		ss << solve(input) << endl;
	
	printf("%s\n", ss.str().c_str());
	
	return 0;
}
