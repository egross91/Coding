/*
ID: eric.bg1
PROG: gift1
LANG: C++11
*/

/*pragma handle-exceptions*/

#include <iostream>
#include <vector>
#include <utility>
#include <unordered_map>
#include <fstream>
#include <stdexcept>

using namespace std;

int main()
{
	ofstream fout ("gift1.out");
    ifstream fin ("gift1.in");
	
	int num_people;
	fin >> num_people;
	
	vector<pair<string,int> > people(num_people);
	unordered_map<string, int> indices; // cache the indices of people's name
	for (int i = 0; i < num_people; ++i) {
		pair<string, int> p;
		fin >> p.first;
		p.second = 0;
		people[i] = p;
		indices[p.first] = i;
	}
	
	int money, num;
	string giver;
	while (fin >> giver) {
		fin >> money >> num;
			
		int giver_idx = indices[giver];
		if (num > 0)
			people[giver_idx].second += ((money % num) - money);
		
		for (int i = 0; i < num; ++i) {
			string receiver;
			fin >> receiver;
			int receiver_idx = indices[receiver];
			people[receiver_idx].second += (money / num);
		}
	}
	
	for (int i = 0; i < num_people; ++i)
		fout << people[i].first << " " << people[i].second << endl;
	
	return 0;
}
