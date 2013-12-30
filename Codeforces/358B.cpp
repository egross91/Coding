#include <iostream>
#include <sstream>

bool check(const std::string& text, const std::string& coded) 
{
	size_t len = coded.length();
	unsigned j = 0;
	for (unsigned i = 0; i != len; ++i)
		if (coded[i] == text[j])
			++j;
			
	return j == text.length();
}

/* bool check(const std::string& text, const std::string& coded) 
{
	size_t size = coded.length();
	if (!(coded[0] == '<' && (coded[size-1] == '<' 
		|| (coded[size-1]-'0' >= 0 && coded[size-1]-'0' <= 9) || coded[size-1] == '>')))
	  return false;
	  
	std::stringstream ss;
	for (unsigned long long i = 2; i != size-1; ++i) {
		if (!(coded[i] == '<' || (coded[i]-'0' >= 0 && coded[i]-'0' <= 9) || coded[i] == '>')) 
			ss << coded[i];
	}
	
	return (ss.str() == text);
} */

int main() 
{
	int n;
	std::cin >> n;
	
	std::string text = "<3";
	for (unsigned i = 0; i != n; ++i) {
		std::string line;
		std::cin >> line;
		text.append(line);
		text.append("<3");
	}
	
	std::string coded;
	std::cin >> coded;
	
	std::cout << (check(text, coded) ? "yes" : "no") << std::endl;
	
	return 0;
}