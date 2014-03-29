#include <iostream>
#include <sstream>

#define LL long long

using namespace std;

/** GLOBALS **/
LL W, H;
ostringstream output;

/** PUBLIC METHODS **/
LL solve();

int main()
{
	while ((cin >> W >> H) && (W && H)) 
		output << solve() << endl;
		
	cout << output.str();
	
	return 0;
}

LL solve()
{
	if (W > H) {
		if (!(W%H))
			return W/H;
		if (!(W & 0x1) && !(H & 0x1))
			return W;
		
		return (W*H);
	}
	else {
		if (!(H%W))
			return H/W;
		if (!(W & 0x1) && !(H & 0x1))
			return H;
			
		return (W*H);
	}
}
