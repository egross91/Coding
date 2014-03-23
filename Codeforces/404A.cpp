#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int N;
	cin >> N;
	
	string line;
	cin.ignore();
	cin >> line;
	const char diag = line[0];
	const char other = line[1];
	
	if (diag == other) {
		cout << "NO";
		return 0;
	}
	
	for (int i = 2; i < line.length(); ++i) {
		if (i == N-1 && line[i] != diag) {
			cout << "NO";
			return 0;
		}
		if (i != N-1 && line[i] != other) {
			cout << "NO";
			return 0;
		}
	}
		
	
	for (int i = 1; i < N; ++i) {
		cin >> line;
		
		for (int j = 0; j < line.length(); ++j) {
			if ((j == i || j == (N-i-1)) && line[j] != diag) {
				cout << "NO";
				return 0;
			}
			if (!(j == i || j == (N-i-1)) && line[j] != other) {
				cout << "NO";
				return 0;
			}
		}
	}
	
	cout << "YES";
	
	return 0;
}