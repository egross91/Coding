#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <map>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

using namespace std;

/** DATA STRUCTURES **/
typedef vector<vector<int> > Graph;

/** METHOD PROTOTYPES **/
Graph getGraph();
int convertStringToInt(const string&);
int process(const Graph&, const int&);
bool isConnected(const Graph&, const int&);
void DFS(const Graph&, const int&, int, bool*);
void split(const string&, char, vector<string>&);
vector<string> split(const string&, char);

/** GLOBALS **/
int N;
int largestSeenNode;

/** ENRTY POINT **/
int main(int argc, char** argv) {
    ostringstream out;

    while ((scanf("%i", &N) == 1) && N) {
        Graph G = getGraph();

        out << process(G, N) << endl;
    }

    cout << out.str();

    return 0;
}

/** HELPER METHODS **/
Graph getGraph() {
    Graph G(N+1);

    string line;
    int node;
    largestSeenNode = 0;
    getline(cin, line); // get dangling /r/n
    while (true) {
        getline(cin, line);
        if (line[0] == '0') break;

        vector<string> tokens = split(line, ' ');
        node = convertStringToInt(tokens[0]);

        for (int i = 1; i < tokens.size(); ++i) {
            int edge = convertStringToInt(tokens[i]);
            G[node].push_back(edge);
            G[edge].push_back(node);
            largestSeenNode = max(largestSeenNode, max(edge, node));
        }
    }

    return G;
}

int convertStringToInt(const string& value) {
    stringstream ss(value);
    int val;
    ss >> val;

    return val;
}

int process(const Graph& G, const int& N) {
    bool connected = isConnected(G, 0);
    int count = 0;

    if (connected) {
        for (int i = 1; i <= N; ++i) {
            if (!isConnected(G, i))
                ++count;
        }
    }
    else {
        bool* visited = new bool[N+1];
        for (int i = 1; i <= N; ++i)
            visited[i] = false;

        for (int i = 1; i <= N; ++i)
            DFS(G, 0, i, visited);

        for (int i = 1; i <= largestSeenNode; ++i)
            if (!visited[i])
                ++count;

        delete [] visited;
    }

    return count;
}

bool isConnected(const Graph& G, const int& deleted) {
    bool* visited = new bool[N+1];
    for (int i = 1; i <= N; ++i)
        visited[i] = false;

    if (deleted != 1)
        DFS(G, deleted, 1, visited);
    else {
        for (int i = 2; i <= N; ++i) {
            if (!G[i].empty()) {
                DFS(G, deleted, 2, visited);
                break;
            }
        }
    }

    // Check for connectivity
    bool connected = true;
    for (int i = 1; i <= N; ++i) {
        if (i != deleted && !visited[i]) {
            connected = false;
            break;
        }
    }

    delete[] visited;
    return connected;
}

void DFS(const Graph& G, const int& deleted,
         int from, bool* visited) {
    if (!G[from].empty())
        visited[from] = true;

    vector<int> neighbors = G[from];
    int edge;
    for (int i = 0; i < neighbors.size(); ++i) {
        edge = neighbors[i];

        if (edge != deleted && !visited[edge])
            DFS(G, deleted, edge, visited);
    }
}

void split(const string& input, char delim, vector<string>& tokens) {
    stringstream ss(input);
    string token;

    while (getline(ss, token, delim))
        if (!token.empty())
            tokens.push_back(token);
}

vector<string> split(const string& input, char delim) {
    vector<string> tokens;
    split(input, delim, tokens);

    return tokens;
}
