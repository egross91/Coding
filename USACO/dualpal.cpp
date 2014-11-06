/*
ID: eric.bg1
PROG: dualpal
LANG: C++
*/
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

ifstream fin("dualpal.in");
ofstream fout("dualpal.out");

string solve();
bool isPalindrome(const int&, const int&);
string convertToBaseB(const int&, const int&);
string reverseString(string);

int main()
{
	string answer = solve();

	fout << answer;

	return 0;
}

string solve()
{
	int N, S;
	fin >> N >> S;

	ostringstream ss;
	int count = 0;

	while (count < N)
	{
		int palins = 0;
		++S;
		for (int base = 2; base <= 10; ++base)
		{
			if (palins >= 2) break;
			if (isPalindrome(base, S))
				++palins;
		}

		if (palins >= 2)
		{
			ss << S << "\n";
			++count;
		}
	}

	return ss.str();
}

bool isPalindrome(const int& base, const int& S)
{
	string ret = convertToBaseB(base, S);

	if (ret[0] == '0')
		return false;

	int high = ret.length() - 1;
	for (int i = 0; i < high; ++i)
		if (ret[i] != ret[high--])
			return false;

	return true;
}

string convertToBaseB(const int& base, const int& S)
{
	int temp = S;
	int rem = S % base;

	stringstream ret;

	while (temp != 0)
	{
		ret << rem;

		temp /= base;
		rem = temp % base;
	}

	return reverseString(ret.str());
}

string reverseString(string str)
{
	string ret = str;
	int high = str.length() - 1;

	for (int i = 0; i < str.length() / 2; ++i)
	{
		char temp = ret[i];
		ret[i] = ret[high];
		ret[high--] = temp;
	}

	return ret;
}