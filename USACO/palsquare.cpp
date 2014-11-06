/*
ID: eric.bg1
PROG: palsquare
LANG: C++
*/
#include <iostream>
#include <cstdio>
#include <sstream>
#include <utility>
#include <fstream>

using namespace std;

ofstream fout ("palsquare.out");
ifstream fin ("palsquare.in");

string solve();
pair<bool, string> isPalindrome(const int&, const int&);
string convertToBaseB(const int&, const int&);
string reverseString(string);

int main()
{
	string answer = solve();

	// printf("%s\n", answer.c_str());
	fout << answer;

	return 0;
}

string solve()
{
	int base;
	fin >> base;

	ostringstream ss;

	for (int i = 1; i <= 300; ++i)
	{
		pair<bool, string> candidate = isPalindrome(base, i);

		if (candidate.first)
		{
			string n = convertToBaseB(base, i);
			ss << n << " " << candidate.second << "\n";
		}
	}

	return ss.str();
}

pair<bool, string> isPalindrome(const int& base, const int& num)
{
	string nSquared = convertToBaseB(base, num * num);

	int high = nSquared.length() - 1;
	for (int i = 0; i < nSquared.length() / 2; ++i)
	{
		if (nSquared[i] != nSquared[high--])
			return pair<bool, string>(false, "");
	}

	return pair<bool, string>(true, nSquared);
}

string convertToBaseB(const int& base, const int& num)
{
	stringstream ss;
	int rem = num % base;
	int temp = num;

	while (temp != 0)
	{
		if (base > 10 && rem >= 10)
		{
			ss << char('A' + (rem - 10));
		}
		else
		{
			ss << rem;
		}
		
		temp /= base;
		rem = temp % base;
	}

	stringstream ret(reverseString(ss.str()));
	return ret.str();
}

string reverseString(string str)
{
	string ret = str;
	int high = str.length() - 1;

	for (int low = 0; low < str.length() / 2; ++low)
	{
		char temp = ret[low];
		ret[low] = ret[high];
		ret[high--] = temp;
	}

	return ret;
}