#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

/** GLOBALS **/
int dr[] = { -1,-1,0,0,1,1 };
int dc[] = { -1,0,1,-1,0,1 };
int N;
int visited[200][200] = { 0 };
char winner;


void determineWinner(const vector<string>&);
void DFS(const int&, const int&, const vector<string>&);

int main()
{
	cin >> N;
	int game = 1;
	while (N) {
		vector<string> graph(N);
		for (int i = 0; i < N; ++i)
			cin >> graph[i];
		
		for (int r = 0; r < N; ++r)
			for (int c = 0; c < N; ++c)	
				visited[r][c] = 0;
	
		
		winner = ' ';
		determineWinner(graph);
		cout << game++ << " " << winner << endl;
		
		cin >> N; 
	}
	return 0;
}

void determineWinner(const vector<string>& graph)
{
	for (int i = 0; i < graph.size(); ++i) {
		if (graph[i][0] == 'w') {
			DFS(i,0,graph);
		}
	}
	if (winner == 'W')
		return;
	else 
		winner = 'B';
}

void DFS(const int& r, const int& c, const vector<string>& graph)
{	
	visited[r][c] = 1;
	for (int i = 0; i < 6; ++i) { // Search all the neighbors
		int newR = r + dr[i];
		int newC = c + dc[i];
		
		if ((newR > -1 && newR < N) && (newC > -1 && newC < N) // graph boundaries
		&& graph[newR][newC] == 'w' && visited[newR][newC] == 0) {
			if (newC == N-1)  {
				winner = 'W';
				return;
			}
			DFS(newR,newC,graph);
		}
	}
}
