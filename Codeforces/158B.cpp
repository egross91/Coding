#include <iostream>

int main()
{
	int n;
	std::cin >> n;
	
	unsigned one, two, thr, fou;
	one = two = thr = fou = 0;
	
	for (unsigned i = 0; i != n; ++i) {
		unsigned temp;
		std::cin >> temp;
		if (temp == 1) ++one;
		else if (temp == 2) ++two;
		else if (temp == 3) ++thr;
		else ++ fou;
	}
	
	unsigned cars = 0;
	cars += fou;
	
	while (one && thr) { // ones & thress
		++cars;
		--one; --thr;
	}
	
	if (thr) // leftover threes and no ones
		cars += thr;
	while (one && two) {
		++ cars;
		--two;
		if (one > 1)
			one -= 2;
		else
			one -= one;
	}
	cars += (two / 2); // twos
	
	bool tw_rem = false;
	if (two%2 != 0) { // remainders of twos
		++cars;
		tw_rem = true;
		two = 1;
	}
	else
		two = 0;
	
	while (tw_rem && one) { 
		++cars;
		if (one > 1)
			one -= 2;
		else
			one -= one;
	}	
	
	while (one) {
		++cars;
		if (one >= 4) 
			one -= 4;
		else 
			one -= one;
	}
	
	std::cout << cars << std::endl;
	
	return 0;
}