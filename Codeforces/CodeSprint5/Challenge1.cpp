#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

vector<long long> fibos(51, 1);


void fib() 
{
	for (unsigned i = 2; i <= 50; ++i)
		fibos[i] = fibos[i-1] + fibos[i-2];
}

int main() {
    int t;
    cin >> t;
    
    long long tests[t];
    
	unsigned i = 0;
    for (; i != t; ++i)
        cin >> tests[i];
    
	
    i = 0;
	fib();
    while (t--) {
        long long n = tests[i++];
        if (n == 0 || n == 1) {
            cout << "IsFibo" << endl;
			continue;
        }
		
		for (unsigned i = 2; i < 51; ++i) {
			if (fibos[i] == n) {
				cout << "IsFibo" << endl;
				break;
			}
			if (fibos[i] > n) {
				cout << "IsNotFibo" << endl;
				break;
			}
		}        
    }
	
	
    return 0;
}
