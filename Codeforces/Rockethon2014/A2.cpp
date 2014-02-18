#include <iostream>
#include <cstdio>
#include <algorithm>

using namespace std;

int main()
{
	int N, Q;
	scanf("%d %d", &N, &Q);
	if (Q == 1) {
		cout << 0;
		return 0;
	}
	
	int* skis = new int[N];
	
	for (int i = 0; i < N; ++i)
		scanf("%d", &skis[i]);
	
	sort(skis,skis+N);
	int packs = 0;
	int high = N-1;
	int low = 0;
	while (low < high) {
		int sum = skis[low]+skis[high];
		if (sum == Q) {
			++packs;
			++low; --high;
			continue;
		}
		if (sum > Q) {
			--high;
			continue;
		}
		if (sum < Q) {
			++low;
			continue;
		}
	}
	printf("%d", packs);
	delete[] skis;
	return 0;
}