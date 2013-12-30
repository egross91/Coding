#include <iostream>

int main()
{
	std::string line;
	std::cin >> line;
	
	unsigned ci;
	for (unsigned i = 0; i != line.length(); ++i) {
		if (line[i] == '^') {
			ci = i;
			break;
		}
	}
	
	long long l, r;
	l = r = 0;
	for (unsigned i = 0; i != line.length(); ++i) {
		if (i < ci && line[i] != '=') {
			unsigned tmp = line[i]-'0';
			l += tmp*(ci-i);
		}
		else if (i > ci && line[i] != '=') {
			unsigned tmp = line[i]-'0';
			r += tmp*(i-ci);
		}
	}
	
	if (l == r) 
		std::cout << "balance";
	else if (l > r)
		std::cout << "left";
	else
		std::cout << "right";
	return 0;
}



/* int main()
{	
	std::string line;
	std::cin >> line;
	
	// Parse through the string
	bool right = false, rn = false;
	int l, r, rf;
	l = r = rf = 0;
	int nums[2];
	nums[0] = nums[1] = 0;
	
	for (unsigned i = 0; i != line.length(); ++i) {
		char c = line[i];
		int cn = c-'0';
		if (c == '=' && !right)
			++l;
		else if (c == '=' && !rn) 
			++r;
		else if (c == '=')
			++rf;
		else if ((cn >= 0 && cn <= 9) && !right) 
			nums[0] += cn;
		else if (cn >= 0 && cn <= 9) {
			nums[1] += cn;
			rn = true;
		}
		else  
			right = true;
		
	}
	
	if ((!nums[0] && !nums[1]) || (nums[0]+l == nums[1]+r-rf)) 
		std::cout << "balance";
	else if ((nums[0] && !nums[1]) || (nums[0]+l > nums[1]+r-rf)) 
		std::cout << "left";
	else 
		std::cout << "right";
	
	return 0;
} */