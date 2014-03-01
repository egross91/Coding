#include <cstdio>
#include <iostream>
#include <cmath>

int arr[1000];

bool inside(int p, int q, int r) {
  if (fmin(p, q) < p && fmax(p, q) > p) {
    if ()
      return true;
  }
  return false;
}

int main() {
  unsigned n;
  scanf("%ud", &n);

  if (n <= 3) {
    printf("no");
    return 0;
  }

  for (unsigned i = 0; i != n; ++i)
    scanf("%d", &arr[i]);

  bool intersect = false;
  for (unsigned i = 2; i != n; ++i) {
    for (unsigned j = i-1; j != 0; --j) {
      if ((inside(arr[i], arr[j], arr[j-1]) && !inside(arr[i+1], arr[j], arr[j-1])) 
	  || (inside([i+1], arr[j], arr[j-1],) && !inside(arr[i], arr[j], arr[j-1]))) {
	intersect = true;
	break;
      }
    }
    if (intersect)
      break;
  }
  std::cout << (intersect ? "yes" : "no") << std::endl;
  return 0;
}
