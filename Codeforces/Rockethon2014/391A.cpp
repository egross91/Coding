#include <iostream>

using namespace std;

int main()
{
	string input;
	cin >> input;
	
	int i = 0;
	int inserts = 0;
	while (i < input.length()) {
		int j = i+1;
		int count = 1;
		char current_char = input[i];
		while (input[j] == current_char) {
			++count; ++j;
		}
		i = j;
		if (!(count & 0x1))
			++inserts;
	}
	
	cout << inserts;
	return 0;
}