#include <cstdio>
#include <fstream>
#include <sstream>

using namespace std;

ofstream fin("output.out");
ifstream in("A-small-attempt7.in");

string solve();

int main()
{
	int tests;
	in >> tests;

	for (int t = 0; t < tests; ++t) {
		fin << "Case #" << (t+1) << ": " << solve();
		if (t+1 < tests)
			fin << "\n";
	}

	return 0;
}

string solve() 
{
	int row1;
	in >> row1;

	int save[5] = { };
	for (int r = 1; r <= 4; ++r) {
		for (int c = 1; c <= 4; ++c) {
			int num;
			in >> num;

			if (r == row1)
				save[c] = num;
		}
	}

	int row2;
	in >> row2;

	int idx, count = 0;
	for (int r = 1; r <= 4; ++r) {
		for (int c = 1; c <= 4; ++c) {
			int num;
			in >> num;

			if (r == row2) {
				for (int i = 1; i <= 4; ++i) {
					if (save[i] == num) {
						++count;
						idx = i;
						break;
					}
				}
			}
		}
	}

	ostringstream ss;
	if (count == 1)
		ss << save[idx];
	else if (count == 0)
		ss << "Volunteer cheated!";
	else // (count > 1)
		ss << "Bad magician!";

	return ss.str();
}