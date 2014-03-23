#include <iostream>
#include <cstdio>
#include <vector>
#include <sstream>
#include <queue>

#define MAX_INT 10000000

using namespace std;

/** DATA STRUCTURE **/
template<typename T1, typename T2>
struct Pair {
	T1 row;
	T2 col;
	
	Pair() : row(0), col(0) { };
	Pair(T1 a, T2 b) : row(a), col(b) { };
}; /// END PAIR STRUCT


/** GLOBALS **/
int W, H;
Pair<int,int> start;
Pair<int,int> end;
const int dr[] = { -1,0,0,1 };
const int dc[] = { 0,1,-1,0 };


/** PUBLIC METHODS **/
int solve(const int[][27]);
int BFS(const int&, const int&, const int[][27], int[][27]);
int min(const int&, const int&);


void printGraph(const int graph[][27])
{
	for (int r = 0; r < H; ++r) {
		for (int c = 0; c < W; ++c) {
			cout << graph[r][c] << "\t";
		}
		cout << endl;
	}
}


int main()
{
	ostringstream ss;
	while (true) {
		scanf("%d %d",&W,&H);
		if (W == 0 && H == 0) break;
		
		int graph[27][27] = { 0 };
		string line;
		for (int r = 0; r < H; ++r) {
			cin >> line;
			for (int c = 0; c < W; ++c) {
				if (line[c] == 'X') {
					graph[r][c] = 0;
					continue;
				}
				else if (line[c] == 'S') {
					graph[r][c] = -1;
					start.row = r;
					start.col = c;
					continue;
				}
				else if (line[c] == 'D') {
					graph[r][c] = -2;
					end.row = r;
					end.col = c;
					continue;
				}
				else
					graph[r][c] = int(line[c]-'0');
			}
		}
		
		ss << solve(graph) << endl;
	}
	
	cout << ss.str();
	
	return 0;
}

int solve(const int graph[][27])
{
	int visited[27][27] = { 0 };
	visited[start.row][start.col] = 1;
	
	return BFS(start.row,start.col,graph,visited);
}

int BFS(const int& r, const int& b,
		const int graph[][27], int visited[][27])
{
	queue<Pair<int,int> > Q;
	Pair<int,int> p1(r,b);
	Q.push(p1);
	
	int paths[27][27] = { 0 };
	
	while (!Q.empty()) {
		Pair<int,int> cur = Q.front();
		
		for (int i = 0; i < 4; ++i) {
			int newR = cur.row + dr[i];
			int newC = cur.col + dc[i];
			
			if ((newR > -1 && newR < H) && (newC > -1 && newC < W)
				&& (visited[newR][newC] == 0 
				|| ((graph[newR][newC] + paths[cur.row][cur.col]) < paths[newR][newC]))
				&& (graph[newR][newC] >= 1 && graph[newR][newC] <= 9)) {
				
				int new_cell = (graph[newR][newC] + paths[cur.row][cur.col]);
				if (visited[newR][newC] == 0) {
					paths[newR][newC] = new_cell;
					visited[newR][newC] = 1;
				}
				else
					paths[newR][newC] = min(paths[newR][newC],new_cell);
				Pair<int,int> p2(newR,newC);
				Q.push(p2);
				continue;
			}
			
			if (graph[newR][newC] == -2) {
				if (visited[newR][newC] == 0) { /// hasn't been visited yet
					paths[newR][newC] = paths[cur.row][cur.col];
					visited[newR][newC] = 1;
				}
				else /// already been visited
					paths[newR][newC] = min(paths[newR][newC],paths[cur.row][cur.col]);
				break;
			}
		}
		visited[cur.row][cur.col] = 1;
		Q.pop();
	}
	
	return paths[end.row][end.col];
}

int min(const int& a, const int& b)
{
	return ((a < b) ? a : b);
}
