#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

ofstream fin("output.out");
ifstream in("B-large.in");

double solve();

int main()
{
	int tests;
	in >> tests;

	for (int t = 1; t <= tests; ++t) 
		fin << "Case #" << t << ": " << fixed << setprecision(7) << solve() << "\n";
	
	return 0;
}

double solve()
{
	double C, F, X;
	in >> C >> F >> X;

	double init = (X / 2);

	int count = 0;
	double prev = 0;
	double total = 0;
	while (true) {
		double per_s = 2 + (count * F);
		double temp_prev = prev + (C / per_s);
		prev += (C / per_s);
		per_s = 2 + (++count * F);

		prev += (X / per_s);
		
		total = temp_prev + (C / per_s); 
		per_s = 2 + (++count * F);
		total += (X / per_s);
		if (total > prev) break;

		prev = temp_prev;
		--count;
	}

	return (prev < init) ? prev : init;
}
