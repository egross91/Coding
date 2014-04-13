#include <iostream>
#include <cstdio>
#include <sstream>
#include <vector>
#include <queue>

using namespace std;

struct Node {
	int num;
	int dist;
	
	Node() : num(0), dist(0) { };
	Node(int _n, int _d) : num(_n), dist(_d) { };
};

struct Comp {
	bool operator() (const Node& n1, const Node& n2) {
		return (n1.dist < n2.dist);
	}
};

typedef priority_queue<Node, vector<Node>, Comp> PQ;
typedef vector<vector<int> > WG; /// Weighted-Graph

/** GLOBALS **/
int N;
ostringstream output;

/** PUBLIC METHODS **/
void solve();
int findMaxPath(const WG&);
int BFS(const WG&, bool*, int);
int max(const int&, const int&);

int main()
{
	cin >> N;
	
	solve();
	
	return 0;
}

void solve()
{
	WG Graph(N);
	for (int i = 0; i < N-1; ++i) {
		int v, u;
		cin >> v >> u;
		
		Graph[v-1].push_back(u-1);
		Graph[u-1].push_back(v-1);
	}
	
	cout << findMaxPath(Graph);
}

int findMaxPath(const WG& G)
{
	int max_path = 0;
	bool* visited = new bool[N];
	for (int i = 0; i < N; ++i)
		visited[i] = false;
		
	
	for (int i = 0; i < N; ++i) 
		if (!visited[i]) 
			max_path = max(max_path,BFS(G,visited,i));
	
	
	return max_path;
}

int BFS(const WG& G, bool* visited, int start)
{
	queue<Node> Q;
	Q.push(Node(start,0));
	
	PQ pri_q;
	int max_dist = 0;
	while (!Q.empty()) {
		int curr_node = Q.front().num;
		int curr_dist = Q.front().dist;
		Q.pop();
		
		visited[curr_node] = true;
		
		vector<int> neighbors = G[curr_node];
		for (int i = 0 ; i < neighbors.size(); ++i) 
			if (!visited[neighbors[i]]) {
				Q.push(Node(neighbors[i],curr_dist+1));
				
				max_dist = max(max_dist,curr_dist+1);
				pri_q.push(Node(neighbors[i],curr_dist+1));
			}
	}


	for (int i = 0; i < N; ++i)
		visited[i] = false;

	int new_start = pri_q.top().num;
	pri_q.pop();
	Q.push(Node(new_start,0));
	while (!Q.empty()) {
		int curr_node = Q.front().num;
		int curr_dist = Q.front().dist;
		Q.pop();
		
		visited[curr_node] = true;
		
		vector<int> neighbors = G[curr_node];
		for (int i = 0 ; i < neighbors.size(); ++i) 
			if (!visited[neighbors[i]]) {
				Q.push(Node(neighbors[i],curr_dist+1));
				
				max_dist = max(max_dist,curr_dist+1);
			}
	}
	
	
	return max_dist;
}

int max(const int& a, const int& b)
{
	return ((a > b) ? a : b);
}
