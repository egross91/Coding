/*
ID: eric.bg1
PROG: beads
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

/** GLOBALS **/
int num_beads;

using namespace std;

int findMostBeads(const string&);
int searchLeft(const string&, char&, int);
int searchRight(const string&, const char&, int);
int max(const int&, const int&);
bool neg(const int&);

int main()
{
	ifstream fin("beads.in");
	ofstream fout("beads.out");
	
	string beads;
	fin >> num_beads;
	fin >> beads;
	
	fout << findMostBeads(beads) << endl;
	
	return 0;
}

int findMostBeads(const string& beads)
{
	int ans = 0;
	for (int idx = 0; idx < num_beads; ++idx) {
		char color = ' ';
		int left = searchLeft(beads,color,idx);
		if (left == num_beads)
			return num_beads;
		
		int right = searchRight(beads,color,((idx+1) % num_beads));
		if (right == num_beads || (left+right) == num_beads)
			return num_beads;
		
		ans = max(ans,(left+right));
	}
	
	if (!ans) // All are w's
		return num_beads;
	
	return ans;
}

int searchLeft(const string& beads, char& color, int idx)
{
	if (beads[idx] == 'w') 
		return 0;
		
	color = beads[idx];
		
	int count = 1;
	for (int cur = idx-1; cur != idx; --cur) {
		if (neg(cur)) // Make sure to stay in bounds.
			cur = num_beads-1;
		
		if (beads[cur] == color || beads[cur] == 'w')
			++count;
		else
			break;
	}
	
	return count;
}

int searchRight(const string& beads, const char& left_color, int idx)
{
	char color;
	if (beads[idx] == 'w' && left_color == 'b') 
		color = 'r';
	else if (beads[idx] == 'w' && left_color == 'r')
		color = 'b';
	else if (beads[idx] == 'w' && left_color == ' ')
		return 0;
	else
		color = beads[idx]; 
	
	int count = 1;
	for (int cur = ((idx+1) % num_beads); (cur % num_beads) != idx; ++cur) {
		if (beads[cur] == color || beads[cur] == 'w')
			++count;
		else
			break;
	}
	
	return count;
}

int max(const int& a, const int& b)
{
	return (a > b) ? a : b;
}

bool neg(const int& a)
{
	return (a < 0);
}
