/*
ID: eric.bg1
PROG: namenum
LANG: C++11
*/

#include <iostream>
#include <list>
#include <vector>
#include <fstream>
#include <sstream>
#include <cmath>
#include <map>

using namespace std;

/** GLOBALS **/
ifstream dict("dict.txt");
ifstream fin("namenum.in");
ofstream fout("namenum.out");


/** PUBLIC METHODS **/

string solve();
string encode(string, map<char,char>);


int main()
{
	fout << solve();

	return 0;
}

string solve()
{
	ostringstream ret;
	map<char, char> decoder;
	decoder['A'] = '2';
	decoder['B'] = '2';
	decoder['C'] = '2';
	decoder['D'] = '3';
	decoder['E'] = '3';
	decoder['F'] = '3';
	decoder['G'] = '4';
	decoder['H'] = '4';
	decoder['I'] = '4';
	decoder['J'] = '5';
	decoder['K'] = '5';
	decoder['L'] = '5';
	decoder['M'] = '6';
	decoder['N'] = '6';
	decoder['O'] = '6';
	decoder['P'] = '7';
	decoder['R'] = '7';
	decoder['S'] = '7';
	decoder['T'] = '8';
	decoder['U'] = '8';
	decoder['V'] = '8';
	decoder['W'] = '9';
	decoder['X'] = '9';
	decoder['Y'] = '9';

	string codedName;
	fin >> codedName; // 4734
	
	string name;
	for (int i = 0; i < 5000; ++i) {
		dict >> name;
		if (name.length() != codedName.length()) continue;

		string encodedName = encode(name, decoder);
		if (encodedName == codedName) 
			ret << name << endl;
	}

	if (ret.str().length() > 0)
		return ret.str();

	ret << "NONE" << endl;
	return ret.str();
}

string encode(string name, map<char,char> decoder)
{
	string ret;
	for (int i = 0; i < name.length(); ++i) {
		char curr_letter = name[i];
		char coded_letter = decoder[curr_letter];

		ret.push_back(coded_letter);
	}

	return ret;
}
