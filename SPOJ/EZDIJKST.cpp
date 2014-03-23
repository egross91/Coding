#include <cstdio>
#include <vector>
#include <queue>
#include <sstream>

#define LL long long
#define INF 2000000000
#define VLL vector<LL>

using namespace std;

/** DATA STRUCTURE **/
struct Pair {
	int edge;
	LL weight;
	Pair() : edge(0), weight(0) { };
	Pair(int b, int c) : edge(b), weight(c) { };
}; /// END Pair STRUCTURE

struct Comp {
	bool operator()(const Pair& node1, const Pair& node2) {
		return (node1.weight > node2.weight);
	}
}; /// END Comp STRUCTURE

typedef priority_queue<Pair, vector<Pair>,Comp> NodeWeights;
typedef vector<vector<Pair> > WG; // Weighted-Graph

/** GLOBALS **/
int V, K, A, B;
WG graph;
VLL distances;

/** PUBLIC METHODS **/
string solve();
void dijkstra();
void relax(const int&, const Pair&, NodeWeights&);
void buildGraph();

int main()
{
	int tests;
	scanf("%d",&tests);
	
	ostringstream ss;
	for (int t = 0; t < tests; ++t) {
		scanf("%d %d", &V, &K);
		
		graph.clear();
		graph.resize(V+1);
		
		buildGraph();
		
		distances.clear();
		distances.resize(V+1, INF);
		
		ss << solve() << "\n";
	}
	
	printf("%s",ss.str().c_str());
	
	return 0;
}

string solve()
{
	scanf("%d %d",&A,&B);
	dijkstra();
	
	ostringstream ss;
	if (distances[B] == INF)
		ss << "NO";
	else
		ss << distances[B];
		
	return ss.str();
}

void dijkstra()
{
	NodeWeights Q;
	
	Pair curr_node(A,0);
	Q.push(curr_node);
	distances[A] = 0;
	
	while (!Q.empty()) {
		curr_node = Q.top();
		
		vector<Pair> neighbors = graph[curr_node.edge];
		Pair curr_edge;
		for (int i = 0; i < neighbors.size(); ++i) {
			curr_edge = neighbors[i];
			
			relax(curr_node.edge,curr_edge,Q);
		}
		
		if (Q.top().edge == B) /// Looked at the Node we want to be at
			return;
		Q.pop();
	}
}

void relax(const int& node, const Pair& edge, NodeWeights& Q)
{
	if (distances[node] + edge.weight < distances[edge.edge]) {
		distances[edge.edge] = distances[node] + edge.weight;
		Q.push(edge);
	}
}

void buildGraph()
{
	int a, b, c;
	Pair edge;
	for (int i = 0; i < K; ++i) {
		scanf("%d %d %d",&a,&b,&c);
		
		edge.edge = b;
		edge.weight = c;
		graph[a].push_back(edge);
	}
}