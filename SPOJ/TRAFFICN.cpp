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

#define INF 1061109567

using namespace std;

/** DATA STRUCTURES **/
struct Node {
    int num;
    long long dist;

    Node() : num(0), dist(0) { };
    Node(int _n, long long _d) : num(_n), dist(_d) { };
}; // End Node struct.
struct MinSort {
    bool operator()(const Node& lhs, const Node& rhs) {
        return (lhs.dist > rhs.dist);
    }
}; // End MinSort struct.
typedef vector<vector<Node> > Graph;
typedef priority_queue<Node, vector<Node>, MinSort> Minheap;

/** METHOD PROTOTYPES **/
void buildGraphs(Graph&, Graph&);
Graph addBiEdge(Graph, int, int, int);
int process(Graph, Graph);
vector<int> dijkstra(Graph, int, int, vector<int>);

/** GLOBALS **/
int N, M, K, S, D;

int main(int argc, char** argv) {
    int T;
    scanf("%i", &T);

    while (T--) {
        scanf("%i %i %i %i %i", &N, &M, &K, &S, &D);

        Graph forward(N+1), reverse(N+1);
        buildGraphs(forward, reverse);
        printf("%i\n", process(forward, reverse));
    }

    return 0;
}

void buildGraphs(Graph& fw, Graph& rv) {
    int d, c, l;
    for (int i = 0; i < M; ++i) {
        scanf("%i %i %i", &d, &c, &l);
        fw[d].push_back(Node(c, l));
        rv[c].push_back(Node(d, l));
    }
}

Graph addBiEdge(Graph G, int u, int v, int q) {
    G[u].push_back(Node(v, q));
    G[v].push_back(Node(u, q));

    return G;
}

int process(Graph fw, Graph rv) {
    vector<int> dsf(N+1, INF), dsb(N+1, INF);
    dsf[S] = dsb[D] = 0;
    dsf = dijkstra(fw, S, D, dsf);
    dsb = dijkstra(rv, D, S, dsb);
    int length = dsf[D];
    int u, v, q;
    for (int i = 0; i < K; ++i) {
        scanf("%i %i %i", &u, &v, &q);

        length = min(dsf[u]+q+dsb[v], min(length, dsf[v]+q+dsb[u]));
    }

    return (length == INF) ? -1 : length;
}

vector<int> dijkstra(Graph G, int start, int stop, vector<int> d) {
    Minheap Q;
    Q.push(Node(start, d[start]));

    Node current, edge;
    while (!Q.empty() && Q.top().num != stop) {
        current = Q.top();
        Q.pop();

        vector<Node> neighbors = G[current.num];
        for (int i = 0; i < neighbors.size(); ++i) {
            edge = neighbors[i];
            if ((current.dist+edge.dist) <= d[edge.num]) {
                Q.push(Node(edge.num, current.dist+edge.dist));
                d[edge.num] = current.dist+edge.dist;
            }
        }
    }

    return d;
}
