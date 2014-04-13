#include <iostream>
#include <cstdio>
#include <fstream>

using namespace std;

int candies;
double money;

ifstream fin("input.txt");

int max(const int&, const int&);

void printDP(int** dp) 
{
	for (int i = 0; i < candies+1; ++i) {
		for (int j = 0; j < int(money)+1; ++j)
			cout << dp[i][j] << " ";
		cout << endl;
	}
}

int main()
{
	while (fin >> candies >> money) {
		if (!candies && money == 0.00D) break;
		
		money *= 100;
		int** dp = new int*[candies+1];
		dp[0] = new int[int(money)+1];
		for (int i = 0; i < int(money)+1; ++i)
			dp[0][i] = 0;
		
		for (int i = 1; i < candies+1; ++i) {
			int calories;
			double cost;
			fin >> calories >> cost;
			cost *= 100;
			
			// cout << calories << " "  << cost << endl;
			
			dp[i] = new int[int(money)+1];
			for (int j = 0; j < int(cost)+1; ++j)
				dp[i][j] = 0;
			
			for (int j = 1; j < int(money)+1; ++j)
				dp[i][j] = max(((j - int(cost) > -1) ? (dp[i][j-int(cost)] + calories) : 0),max(dp[i-1][j], dp[i][j-1]));
		}
		
		// printDP(dp);
		
		printf("%i\n", dp[candies][int(money)]);
	}
	
	return 0;
}

int max(const int& a, const int& b)
{
	return ((a >  b) ? a : b);
}
