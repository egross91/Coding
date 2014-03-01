#include <iostream>
#include <string>
#include <sstream>

std::string reverse(std::string str)
{
  std::string temp;
  // Reverse strings
  for (long long i = str.length()-1; i >= 0; --i)
    temp += str[i];
  return temp; 
}

std::string convertIntToStr(long long num)
{
  std::stringstream ss;
  ss << num;
  return ss.str();
}

long long convertStrToInt(std::string str)
{
  std::stringstream ss(str);
  long long num;
  ss >> num;
  return num;
}

std::string trim(std::string str)
{
  std::string temp;
  bool front = true;
  for (long long i = 0; i < str.length(); ++i)
  {
    if (str[i] == 48 && front)
	{
	  // Pass
	}
	else 
	{
	  temp += str[i];
	  front = false;
	}
  }
  return temp;
}

int main()
{
  int t;
  std::cin >> t;
  
  while (t--)
  {
    long long left, right;
	std::cin >> left >> right;
	
	std::string first = convertIntToStr(left);
	std::string second = convertIntToStr(right);
	
	// Reverse the string-ints.
	first = reverse(first);
	second = reverse(second);
	
	// Convert the reversed strings back into ints.
	left = convertStrToInt(first);
	right = convertStrToInt(second);

	// Sum the ints, then reverse the result.
	long long result = left + right;
	std::string ret = convertIntToStr(result);
	ret = reverse(ret);
	
	// Remove leading zeroes, if any.
	if (ret[0] == 48)
	   ret = trim(ret);
	  
	result = convertStrToInt(ret);
	std::cout << result << std::endl;
  }
  return 0;
}