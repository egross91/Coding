#include <cstdio>

using namespace std;

int reqs[3001] = { 0 };
int comps[3001] = { 0 };

int main()
{
	int N, M;
	scanf("%d %d",&N,&M);
	
	for (int i = 0; i < N; ++i)
		scanf("%d",&reqs[i]);
	
	for (int i = 0; i < M; ++i) 
		scanf("%d",&comps[i]);
	
	int i, j;
	i = j = 0;
	while (i < N && j < M) {
		if (reqs[i] <= comps[j]) {
			++i; ++j;
		}
		else
			++j;
	}
	
	printf("%d", (N-i));
	
	return 0;
}