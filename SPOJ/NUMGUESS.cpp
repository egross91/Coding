#include <iostream>

int main()
{
  int a, b;
  std::cin >> a >> b;
  
  long long i = (a+b)/2;
  while (true)
  {
    std::cout << i << std::endl;
	fflush(stdout);
	std::string ret;
	std::cin >> ret;
    if (ret == "HIGH") 
	  --i;
	else if (ret == "LOW")
	  ++i;
	else
	  break;
  }
  return 0;
}