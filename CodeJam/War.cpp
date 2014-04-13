#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
ofstream fout("output.out");
ifstream in("D-large.in");

void buildvectors(vector<double>&, vector<double>&);
int solveNormal(vector<double>, vector<double>);
int solveOptimal(vector<double>, vector<double>);

int main()
{
	int tests;
	in >> tests;

	for (int t = 1; t <= tests; ++t) {
		in >> n;

		vector<double> naomi(n);
		vector<double> ken(n);

		buildvectors(naomi, ken);

		sort(naomi.begin(), naomi.end());
		sort(ken.begin(), ken.end());

		fout << "Case #" << t << ": " << solveOptimal(naomi, ken) << " " << solveNormal(naomi, ken) << "\n";
	}

	return 0;
}

void buildvectors(vector<double>& naomi, vector<double>& ken)
{
	for (int i = 0; i < n; ++i) 
		in >> naomi[i];

	for (int i = 0; i < n; ++i)
		in >> ken[i];
}

int solveOptimal(vector<double> naomi, vector<double> ken)
{
	int wins = 0;
	bool* used = new bool[n];
	for (int i = 0; i < ken.size(); ++i)
		used[i] = false;

	for (int c = 0; c < n; ++c) {
		double val = ken[c];
		
		bool found = false;
		for (int i = 0; i < n; ++i) {
			if (naomi[i] > val && !used[i]) {
				// cout << val << " " << naomi[i] << endl;

				used[i] = true;
				found = true;
				++wins;
				break;
			}
		}

		if (!found) 
			break;
	}

	delete [] used;
	return wins;
}

int solveNormal(vector<double> naomi, vector<double> ken)
{
	int wins = n;
	bool* used = new bool[n];
	for (int i = 0; i < n; ++i)
		used[i] = false;

	for (int i = 0; i < n; ++i) {
		double val = naomi[i];

		bool found = false;
		for (int j = 0; j < n; ++j) {
			if (ken[j] > val && !used[j]) {
				used[j] = true;
				found = true;
				--wins;
				break;
			}
		}

		if (!found)
			break;
	}

	delete [] used;
	return wins;	
}
