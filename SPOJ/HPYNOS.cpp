#include <cstdio>
#include <map>

using namespace std;

long split(long num)
{
	long split_num = 0;
	while (num > 0) {
		long dig = num % 10;
		split_num += (dig*dig);
		
		num /= 10;
	}
	
	return split_num;
}

long solve(long N)
{
	long count = 0;
	map<long, int> hset;
	bool isHappy = true;
	while (N != 1) {
		long new_num = split(N);
		
		++count;
		if (new_num == 1)
			break;
		if (hset[new_num]++ == 1) {
			isHappy = false;
			break;
		}
		
		N = new_num;
	}
	
	if (isHappy)
		return count;
	
	return -1;
}

int main()
{
	long N;
	scanf("%ld", &N);
	
	printf("%ld\n", solve(N));
	
	return 0;
}
