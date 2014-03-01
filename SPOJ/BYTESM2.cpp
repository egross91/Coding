#include <iostream>

using namespace std;

int matrix[101][101] = { 0 };
int M;
int N;

void buildMatrix();
int findMaxPath();
int max(int,int);

int main()
{
	int tests;
	cin >> tests;
	
	for (int i = 0; i < tests; ++i) {
		cin >> M >> N;
		
		buildMatrix();
		
		int max = findMaxPath();
		cout << max << endl;
	}
	
	return 0;
}

void buildMatrix() {
	for (int r = 0; r < M; ++r) 
		for (int c = 0; c < N; ++c)
			cin >> matrix[r][c];
}

int findMaxPath() {
	int temp = 0;
	for (int r = M-2; r >= 0; --r) {
		for (int c = 0; c < N; ++c) {
			for (int i = c-1; i <= c+1; ++i) {
				if (i > -1 && i < N)
					temp = max(temp,matrix[r+1][i]);
			}
			matrix[r][c] += temp;
			temp = 0;
		}
	}
	
	int pathMax = 0;
	for (int k = 0; k < N; ++k) 
		pathMax = ((matrix[0][k] > pathMax) ? matrix[0][k] : pathMax);
		
	return pathMax;
}

int max(int a, int b) {
	return ((a > b) ? a : b);
}
