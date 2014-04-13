#include <iostream>

using namespace std;

class UnionFind {
private:
	int* parents;
	int graphs;

public:
	UnionFind() : parents(NULL), graphs(0) { };
	UnionFind(int _n) : parents(new int[_n]), graphs(_n) { 
		for (int i = 0; i < _n; ++i)
			parents[i] = -1;
	};
	
	~UnionFind() { delete[] parents; };
	
	int find(int x) {
		if (parents[x] == -1)
			return x;
			
		return parents[x] = find(parents[x]);
	}
	
	bool merge(int x, int y) {
		int xRep = find(x);
		int yRep = find(y);
		
		if (xRep == yRep)
			return false;
		
		parents[xRep] = yRep;
		--graphs;
		return true;
	}
	
	int getGraphs() { return graphs; }
}; // END UionFind CLASS

/** GLOBALS **/
int N, M;

void solve(UnionFind&);

int main()
{
	cin >> N >> M;
	
	UnionFind UF(N);
	solve(UF);
	
	return 0;
}

void solve(UnionFind& UF)
{
	for (int i = 0; i < M; ++i) {
		int a, b;
		cin >> a >> b;
		
		if (!UF.merge(a-1,b-1)) {
			cout << "NO";
			return;
		}
	}
	if (UF.getGraphs() == 1)
		cout << "YES";
}
