#include <iostream>

int main()
{
  int n, m, songs[100001];
  std::cin >> n >> m;
  
  songs[0] = 0; // Zero songs play zero moments.
  for (unsigned i = 0; i != n; ++i)
  {
    int c, t;
    std::cin >> c >> t;
	songs[i+1] = songs[i] + (c * t);
  }
  
  int song = 1; // Starting at song 1.
  while (m--) // Print m moments.
  {
    int moment;
	std::cin >> moment;
	
	while (moment < songs[song])
	  ++song;
	  
	std::cout << song << std::endl;
  }
  return 0;
}