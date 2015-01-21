#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>
#include <stack>
#include <map>
#include <list>
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
int convertStringToInt(string);
void split(const string&, char, vector<string>&);
vector<string> split(const string&, char);
string process(const Graph&, const int&, const int&);
void DFS(const Graph&, int, bool*);

/** ACCESS POINT **/
int main(int argc, char** argv) {
    string line;
    ostringstream output;
    while (getline(cin, line) && line[0] != '0') { // All tests
        int N = convertStringToInt(line);
        Graph G(N+1);

        while (getline(cin, line) && line[0] != '0') { // Build graph
            vector<string> vertices = split(line, ' ');
            int node = convertStringToInt(vertices[0]);

            for (int i = 1; i < vertices.size()-1; ++i)
                G[node].push_back(convertStringToInt(vertices[i]));
        }
        getline(cin, line);
        vector<string> startNodes = split(line, ' ');

        for (int i = 1; i < startNodes.size(); ++i)
            output << process(G, N, convertStringToInt(startNodes[i])) << endl;
    }

    cout << output.str();

    return 0;
}

/** HELPER METHODS **/
int convertStringToInt(string str) {
    stringstream ss(str);
    int value;
    ss >> value;

    return value;
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

string process(const Graph& G, const int& N, const int& start) {
    bool* visited = new bool[N+1];
    for (int i = 1; i <= N; ++i)
        visited[i] = false;

    DFS(G, start, visited);

    vector<int> unvisited;
    for (int i = 1; i <= N; ++i)
        if (!visited[i])
            unvisited.push_back(i);

    ostringstream out;
    out << unvisited.size();
    for (int i = 0; i < unvisited.size(); ++i)
        out << " " << unvisited[i];

    return out.str();
}

void DFS(const Graph& G, int current, bool* visited) {
    vector<int> neighbors = G[current];
    int node;
    for (int i = 0; i < neighbors.size(); ++i) {
        node = neighbors[i];

        if (!visited[node]) {
            visited[node] = true;
            DFS(G, node, visited);
        }
    }
}
