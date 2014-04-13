#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>
#include <iomanip>
#include <fstream>

#define INF 2000000000

using namespace std;

/** DATA STRUCTURES **/
struct Coords {
	int x;
	int y;
	
	Coords() : x(0), y(0) { };
	Coords(int _x, int _y) : x(_x), y(_y) { };
}; /// END Coords STRUCT

/** GLOBALS **/
int N;
vector<Coords> people;
vector<double> memo;

// ifstream cin("input.txt");

/** PUBLIC METHODS **/
double complete(int);
double eucDist(const int&, const int&);
bool done(const int&);
int abs(const int&);
double max(const double&, const double&);

int main()
{
	ostringstream output;
	
	int test = 1;
	while (true) {
		cin >> N;
		// END INPUT
		if (!N) break;
		
		int x, y;
		string dump;
		
		people.clear();
		people.resize(2*N);
		for (int i = 0; i < 2*N; ++i) {
			cin >> dump >> x >> y;
			
			// cout << dump << " " << x << " " << y << endl;

			Coords curr(x,y);
			people[i] = curr;
		}
		
		memo.clear();
		memo.resize(pow(2,2*N), -1);
		
		output << "Case " << test++ << ": " << fixed << setprecision(2) << complete(0) << endl;
	}
	
	cout << output.str();
	
	return 0;
}

double complete(int bitmask)
{
	if (done(bitmask))
		return 0;

	if (memo[bitmask] != -1)
		return memo[bitmask];
		
	double best = double(INF);
	Coords p1;
	int i;
	for (i = 0; i < 2*N; ++i) {
		if (bitmask & (1 << i))
			continue;

		p1 = people[i];
		break;
	}

	for (int j = 0; j < 2*N; ++j) {
			if ((bitmask & (1 << j)) || j == i)
				continue;

			int new_bitmask =  bitmask | (1 << i) | (1 << j);

			// cout << new_bitmask << " " << bitmask << endl;

			Coords p2 = people[j];

			double dist = eucDist(abs(p1.x - p2.x), abs(p1.y - p2.y)) + complete(new_bitmask);
		
			best = min(best, dist);
	}

	memo[bitmask] = best;

	return best;
}

double eucDist(const int& a, const int& b)
{
	return (sqrt((a*a)+(b*b)));
}

bool done(const int& bitmask)
{
	return (bitmask == (pow(2,2*N)-1));
}

int abs(const int& n)
{
	return ((n > -1) ? n : -n);
}

double min(const double& a, const double& b)
{
	return (!(a > b) ? a : b);
}
