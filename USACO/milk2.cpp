/*
ID: eric.bg1
PROG: milk2
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

/** GLOBALS **/
int N;

/** PUBLIC METHODS **/
int longestMilking(const vector<pair<int,int> >&);
int longestNoMilking(const vector<pair<int,int> >&);
int max(const int&, const int&);
int min(const int&, const int &);
bool compare(const pair<int,int>&, const pair<int,int>&);

void printVector(const vector<pair<int,int> >&, ofstream&);

int main()
{
	ifstream fin("milk2.in");
	ofstream fout("milk2.out");
	
	fin >> N;
	
	vector<pair<int,int> > intervals(N);
	pair<int,int> p;
	for (int i = 0; i < N; ++i) {
		fin >> p.first >> p.second;
		intervals[i] = p;
	}
	sort(intervals.begin(),intervals.end(),compare);
	
	/// printVector(intervals,fout);
	fout << longestMilking(intervals) << " " << longestNoMilking(intervals) << endl;
	
	return 0;
}

int longestMilking(const vector<pair<int,int> >& intervals)
{
	int max_milk = 0;
	int prev_first = intervals[0].first;
	int prev_second = intervals[0].second;
	int cur_first;
	int cur_second;
	int conseq_min = intervals[0].first;
	int conseq_max = intervals[0].second;
	
	bool conseq = false;
	for (int i = 0; i < N; ++i) {
		cur_first = intervals[i].first;
		cur_second = intervals[i].second;
		
		if (cur_first <= prev_second || 
			(cur_first <= conseq_max && conseq)) {
			
			int diff;
			if (conseq) {
				conseq_max = max(conseq_max,cur_second);
				diff = conseq_max - conseq_min;
				max_milk = max(max_milk,diff);
			}
			else {
				diff = cur_second - prev_first;
				max_milk = max(max_milk, diff);
				conseq_min = prev_first;
				conseq_max = max(cur_second,prev_second);
				conseq = true;
			}
		}
		else {
			int milk = cur_second - cur_first;
			max_milk = max(max_milk, milk);
			conseq = false;
		}
		prev_first = cur_first;
		prev_second = cur_second;
	}
	
	return max_milk;
}

int longestNoMilking(const vector<pair<int,int> >& intervals)
{
	int max_no_milk = 0;
	int prev_first = intervals[0].first;
	int prev_second = intervals[0].second;
	int cur_first;
	int cur_second;
	int min_first = intervals[0].first;
	int max_second = intervals[0].second;
	
	for (int i = 0; i < N; ++i) {
		cur_first = intervals[i].first;
		cur_second = intervals[i].second;
		
		if (cur_first > max_second) {
			if (cur_second <= max_second) 
				max_no_milk = 0;
			
			else {
				int diff_max = cur_first - max_second;
				max_no_milk = max(max_no_milk,diff_max);
			}
		}
		
		max_second = max(max_second,cur_second);
		prev_first = cur_first;
		prev_second = cur_second;
	}
	
	return max_no_milk;
}

int max(const int& a, const int& b)
{
	return ((a > b) ? a : b);
}

int min(const int& a, const int& b)
{
	return ((a < b) ? a : b);
}

bool compare(const pair<int,int>& one, const pair<int,int>& two)
{
	return (one.first < two.first);
}

void printVector(const vector<pair<int,int> >& intervals, ofstream& fout)
{
	for (int i = 0; i < intervals.size(); ++i)
		fout << intervals[i].first << " " << intervals[i].second << endl;
}