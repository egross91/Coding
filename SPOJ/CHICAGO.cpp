#include <iostream>
#include <vector>
#include <queue>
#include <sstream>
#include <iomanip>

using namespace std;

/** DATA STRUCTURE **/
struct Node {
	int num;
	double weight;
	
	Node() : num(1), weight(1.0) { };
	Node(int _n, int _w) : num(_n), weight(_w) { };
}; /// END Node STRUCTURE

struct Comp {
	bool operator()(const Node& n1, const Node& n2) {
		return (n1.weight < n2.weight);
	}
};

typedef priority_queue<Node, vector<Node>, Comp> Pri_Q;
typedef vector<vector<Node> > WG;

/** GLOBALS **/
int N, M;

/** PUBLIC METHOD **/
WG buildGraph();
string solve(WG&);
void dijkstra(WG& graph, vector<double>&);
void relax(const Node&, const Node&, vector<double>&, Pri_Q&);

int main()
{
	ostringstream ss;
	while (true) {
		cin >> N;
		if (!N) break;
		cin >> M;
		
		WG graph = buildGraph();
		
		ss << solve(graph) << "\n";
	}
	
	cout << ss.str();
	
	return 0;
}

WG buildGraph()
{
	WG graph(N+1);
	int a, b;
	double p;
	Node curr;
	for (int i = 0; i < M; ++i) {
		cin >> a >> b >> p;
		
		curr.weight = p;
		
		curr.num = a;
		graph[b].push_back(curr);
		
		curr.num = b;
		graph[a].push_back(curr);
	}
	
	return graph;
}

string solve(WG& graph)
{
	vector<double> probs;
	probs.resize(N+1, 0.0);
	
	dijkstra(graph,probs);
	
	ostringstream ss;
	ss << fixed << setprecision(6) << (probs[N]*100) << " percent";
	
	return ss.str();
}

void dijkstra(WG& graph, vector<double>& probs)
{
	Pri_Q Q;
	probs[1] = 1.0;
	
	Node curr_node;
	Q.push(curr_node);
	
	while (!Q.empty()) {
		curr_node = Q.top();
		Q.pop();
		
		vector<Node> neighbors = graph[curr_node.num];
		for (int i = 0; i < neighbors.size(); ++i) {
			Node curr_edge = neighbors[i];
			
			relax(curr_node,curr_edge,probs,Q);
		}
		
		if (curr_node.num == N)
			break;
	}
}

void relax(const Node& curr, const Node& edge, vector<double>& probs, Pri_Q& Q)
{
	Node new_node;
	if (((probs[curr.num]*edge.weight) / 100) > probs[edge.num]) {
		probs[edge.num] = ((probs[curr.num]*edge.weight) / 100);
		new_node.num = edge.num;
		new_node.weight = edge.weight;
		Q.push(new_node);
	}
}
