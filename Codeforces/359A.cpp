#include <cstdio>

int m, n;
int table[50][50];

int solve() {
  for (unsigned i = 0; i != m; ++i)
    if (table[i][0] || table[i][n-1]) 
	  return 2;
  for (unsigned j = 0; j != n; ++j)
    if (table[0][j] || table[m-1][j])
	  return 2;
	  
  return 4;
}

int main() {
  scanf("%d%d", &m, &n);
  
  for (unsigned i = 0; i != m; ++i) {
    for (unsigned j = 0; j != n; ++j)
	  scanf("%d", &table[i][j]);
  }
  
  printf("%d\n", solve());
  return 0;
}