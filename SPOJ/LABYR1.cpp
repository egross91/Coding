#include <iostream>
#include <cstdio>
#include <sstream>
#include <vector>
#include <queue>
#include <utility>

using namespace std;

/** GLOBALS **/
int W, H;
int max_length;
const int dr[] = { 0,1,0,-1 };
const int dc[] = { 1,0,-1,0 };
pair<int,int> end;

/** PUBLIC METHODS **/
void buildGraphAndQueue(vector<vector<int> >&,queue<pair<int,int> >&);
void search(vector<vector<int> >&,queue<pair<int,int> >&);
void DFS(vector<vector<int> >&,vector<vector<int> >&,int&,int&,int&,int&,int);
int max(const int&, const int&);
bool inBounds(const int&,const int&);

void printGraph(const vector<vector<int> >& graph)
{
	for (int r = 0; r < H; ++r) {
		for (int c = 0; c < W; ++c)
			cout << graph[r][c] << " ";
		cout << endl;
	}
}

int main()
{
	int tests;
	cin >> tests;
	
	ostringstream ss;
	for (int t = 0; t < tests; ++t) {
		cin >> W >> H; /// Width and Height, respective
		
		vector<vector<int> > graph;
		graph.resize(H+1, vector<int>(W+1,0));
			
		queue<pair<int,int> > Q;
		buildGraphAndQueue(graph,Q);
		search(graph,Q);
		
		ss << "Maximum rope length is " << max_length << ".\n";
	}
	
	cout << ss.str();
	
	return 0;
}

void buildGraphAndQueue(vector<vector<int> >& graph, queue<pair<int,int> >& Q)
{
	max_length = 0;

	string line;
	pair<int,int> p;
	for (int r = 0; r < H; ++r) {
		cin >> line;
		for (int c = 0; c < line.length(); ++c) {
			if (line[c] == '.') {
				graph[r][c] = 0;
				p.first = r;
				p.second = c;
				Q.push(p);
			}
			else /// (line[c] == '#')
				graph[r][c] = -1;
		}
	}
}

void search(vector<vector<int> >& graph, queue<pair<int,int> >& Q)
{
	/// Build visited[][]
	vector<vector<int> > visited;
	visited.resize(H+1, vector<int>(W+1,0));
	
	/// Search all nodes that were found that are potential to visit.
	while (!Q.empty()) {
		pair<int,int> cur = Q.front();
		
		if (visited[cur.first][cur.second] == 0)
			DFS(graph,visited,cur.first,cur.second,cur.first,cur.second,0);
			
		Q.pop();
	}
	
	/// One last search to find the max path
	visited.clear();
	visited.resize(H+1, vector<int>(W+1,0));
	
	DFS(graph,visited,end.first,end.second,end.first,end.second,0);
}

void DFS(vector<vector<int> >& graph, vector<vector<int> >& visited,
		 int& r, int& c, int& prevR, int& prevC, int dist)
{
	if (dist > max_length) {
		max_length = max(max_length,dist);
		end.first = r;
		end.second = c;
	}
	
	for (int i = 0; i < 4; ++i) {
		int newR = r + dr[i];
		int newC = c + dc[i];
		
		if (inBounds(newR,newC) /// Bounds of Graph
			&& (visited[newR][newC] == 0 || dist+1 > graph[newR][newC]) /// Hasn't been visited
			&& (newR != prevR || newC != prevC)
			&& graph[newR][newC] != -1) { /// Where we want to look
			
			visited[newR][newC] = 1;
			graph[newR][newC] = dist+1;
			DFS(graph,visited,newR,newC,r,c,dist+1);
		}
	}
}

int max(const int& a, const int& b)
{
	return ((a > b) ? a : b);
}

bool inBounds(const int& r, const int& c)
{
	return ((r > -1 && r < H) && (c > -1 && c < W));
}
