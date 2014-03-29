#include <iostream>
#include <cmath>
#include <sstream>
#include <iomanip>

#define sqr 1.7320508075688772935274463415059

using namespace std;

/** GLOBALS **/
ostringstream output;
double a,b,c;

/** PUBLIC METHODS **/
double solve();

int main()
{
	int n;
	cin >> n;
	
	while (n--) {
		cin >> a >> b >> c;
		
		output << fixed << setprecision(2) << solve() << endl;
	}
	
	cout << output.str();
	
	return 0;
}

double solve()
{
	double s = (a+b+c)/2;
	
	double A = sqrt((s*(s-a)*(s-b)*(s-c)));
	
	double S = (((a*a*sqr) / 4) + ((b*b*sqr / 4)) + ((c*c*sqr) / 4)
					+ (3*A)) / 2;
	
	return S;
}
