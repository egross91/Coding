#include <iostream>
#include <vector>

unsigned n, k;

int check(const std::vector<int>& scores, const int s)
{
	unsigned winners = 0;
	for (unsigned i = 0; i != n; ++i)
		if (scores[i] >= s && scores[i] > 0) 
			++winners;
			
	return winners;
}

int main()
{
	std::cin >> n >> k;
	std::vector<int> scores(n);
	
	for (unsigned i = 0; i != n; ++i)
		std::cin >> scores[i];

		
	std::cout << check(scores, scores[k-1]) << std::endl;
	return 0;
}