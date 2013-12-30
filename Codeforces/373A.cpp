#include <iostream>
#include <map>

unsigned k;
std::map<char, int> mymap;

bool check() 
{
	std::map<char, int>::iterator itr = mymap.begin(), end = mymap.end();
	for (; itr != end; ++itr) {
		if (itr->first == '.')
			continue;
		else if (itr->second > k*2) 
			return false;
	}
	return true;
}

int main() 
{
	std::cin >> k;
	
	for (unsigned short i = 0; i != 4; ++i) {
		char temp;
		for (unsigned short j = 0; j != 4; ++j) {
			std::cin >> temp;
			++mymap[temp];
		}	
	}
	
	std::cout << (check() ? "YES" : "NO") << std::endl;
	return 0;
}