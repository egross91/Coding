#include <iostream>
#include <sstream>

using namespace std;


string solve(const string& input, const int& N)
{
	ostringstream ss;

	const size_t length = input.length();
	int gap1 = (2*N)-1;
	int gap2 = 1;
	for (int i = 0; i < N; ++i) {
		ss << input[i];
		int temp_gap2 = gap2;
		for (int index = i+gap1; index < length; index += gap1) { 
			ss << input[index];
			//cout << input[index] << endl;
			index += gap2;
			if (index >= length) break;
			ss << input[index];
			//cout << input[index] << endl;
		}
		gap1 -= 2;
		gap2 += 2;
	}
	
	return ss.str();
}

int main()
{
	int N;
	string input;
	ostringstream ss;
	while (cin >> N) {
		if (!N) break; // N == 0
		
		cin >> input;
		ss << solve(input,N) << endl;
	}
	
	cout << ss.str() << endl;
	return 0;
}
