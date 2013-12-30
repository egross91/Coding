#include <iostream>

int main() {
  std::string one, two;
  std::cin >> one >> two;
  size_t length = one.length();
  bool is_trans = true;
  for (unsigned i = 0; i != length; ++i) {
    if (one[i] != two[length-i-1]) {
	  is_trans = false;
	  break;
	}
  }
  std::cout << (is_trans ? "YES" : "NO") << std::endl;
  return 0;
}