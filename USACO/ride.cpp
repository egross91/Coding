/*
ID: eric.bg1
PROG: ride
LANG: C++
*/
#include <iostream>
#include <fstream>

using namespace std;

string decide(const string&, const string&);

int main() 
{
    ofstream fout ("ride.out");
    ifstream fin ("ride.in");
	string comet;
	while (fin >> comet) {
		string group;
		fin >> group;
		
		fout << decide(comet, group) << endl;
	}
	
    return 0;
}

string decide(const string& comet, const string& group)
{
	int cometNum = 1;
	for (int i = 0; i < comet.length(); ++i)
		cometNum *= (int)(comet[i] - 64);
		
	int groupNum = 1;
	for (int i = 0; i < group.length(); ++i)
		groupNum *= (int)(group[i] - 64);
		
	if ((cometNum % 47) == (groupNum %47))
		return "GO";
	else
		return "STAY";
}
