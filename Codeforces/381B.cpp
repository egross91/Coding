#include <iostream>
#include <sstream>

using namespace std;

unsigned cards[5001] = { };

string convert(const unsigned num) 
{
	stringstream ss;
	ss << num;
	return ss.str();
}

int main()
{
	int m;
	cin >> m;
	
	unsigned max = 0;
	for (unsigned i = 0; i != m; ++i) {
		unsigned tmp;
		cin >> tmp;
		cards[tmp]++;
		if (tmp > max)
			max = tmp;
	}
	
	string ret;
	unsigned num = 0;
	for (unsigned i = 1; i <= max-1; ++i) {
		if (cards[i] > 1) {
			ret.append(convert(i));
			ret.append(" ");
			++num;
		}
	}
	
	
	ret.append(convert(max));
	ret.append(" ");
	++num;
	
	for (unsigned i = max-1; i >= 1; --i) {
		if (cards[i] > 0) {
			ret.append(convert(i));
			++num;
		}
		if (i != 1)
			ret.append(" ");
	}
	cout << num << endl;
	cout << ret;
	
	return 0;
}