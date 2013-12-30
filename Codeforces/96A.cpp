#include <iostream>

bool isDangerous(const std::string& line)
{
	size_t len = line.length();
	if (len < 7)
		return false;
		
	unsigned count = 1;
	char curr = line[0];
	for (unsigned i = 1; i != len; ++i) {
		if (line[i] == curr) {
			if (++count == 7)
				return true;
		}
		else {
			curr = line[i];
			count = 1;
		}
	}
	return false;
}

int main()
{
	std::string line;
	std::cin >> line;
	
	std::cout << (isDangerous(line) ? "YES" : "NO") << std::endl;
	return 0;
}