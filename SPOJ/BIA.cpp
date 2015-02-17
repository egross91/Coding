#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

/** DATA STRUCTURES **/
typedef vector<vector<int> > VVI;
typedef vector<int> VI;

/** METHOD PROTOTYPES **/
void process();
void dfs(int);
void link(int, int);
int eval(int);
void compress(int);

/** GLOBALS **/
int N, M, A, B, t;
VVI succs, preds, bucket;
VI parent, semi, vertex, ancestor, label, size, child, dom;

int main() {
    while (scanf("%i %i", &N, &M) == 2) {
        succs = VVI(N+1);
        for (int i = 0; i < M; ++i) {
            scanf("%i %i", &A, &B);
            succs[A].push_back(B);
        }

        process();
        vector<int> doms;
        for (int i = 2; i <= N; ++i)
            doms.push_back(dom[i]);
        sort(doms.begin(), doms.end());
        vector<int>::iterator itr = unique(doms.begin(), doms.end());
        printf("%i\n", itr-doms.begin());

        for (vector<int>::iterator arts = doms.begin(); arts != itr; ++arts)
            printf("%i ", *arts);
        printf("\n");
    }

    return 0;
}

void process() {
    parent = semi = vertex = ancestor = label = size = child = dom = VI(N+1, 0);
    preds = bucket = VVI(N+1);

    t = 0;
    dfs(1);

    int w, i, j, u, v;
    for (i = N; i >= 2; --i) {
        w = vertex[i];

        for (j = 0; j < preds[w].size(); ++j) {
            v = preds[w][j];
            u = eval(v);
            if (semi[u] < semi[w])
                semi[w] = semi[u];
        }

        bucket[vertex[semi[w]]].push_back(w);
        link(parent[w], w);
        for (j = 0; j < bucket[parent[w]].size(); ++j) {
            v = bucket[parent[w]][j];
            u = eval(v);
            dom[v] = (semi[u] < semi[v]) ? u : parent[w];
        }
    }

    for (int i = 2; i <= N; ++i) {
        w = vertex[i];
        if (dom[w] != vertex[semi[w]])
            dom[w] = dom[dom[w]];
    }

    dom[1] = 0;
}

void dfs(int v) {
    semi[v] = ++t;
    vertex[t] = label[v] = v;
    ancestor[v] = child[v] = 0;
    size[v] = 1;

    int w;
    for (int i = 0; i < succs[v].size(); ++i) {
        w = succs[v][i];
        if (!semi[w]) {
            parent[w] = v;
            dfs(w);
        }

        preds[w].push_back(v);
    }
}

void link(int v, int w) {
    int s = w;
    while (semi[label[w]] < semi[label[child[s]]]) {
        if (size[s] + size[child[child[s]]] >= 2*size[child[s]]) {
            ancestor[child[s]] = s;
            child[s] = child[child[s]];
        }
        else {
            size[child[s]] = size[s];
            s = ancestor[s] = child[s];
        }
    }

    label[s] = label[w];
    size[v] = size[v] + size[w];
    if (size[v] < 2*size[w])
        swap(s, child[v]);

    while (s) {
        ancestor[s] = v;
        s = child[s];
    }
}

int eval(int v) {
    if (!ancestor[v]) return label[v];
    compress(v);

    return (semi[label[ancestor[v]]] >= semi[label[v]]) ? label[v] : label[ancestor[v]];
}

void compress(int v) {
    if (!ancestor[ancestor[v]]) return;
    compress(ancestor[v]);

    if (semi[label[v]] > semi[label[ancestor[v]]])
        label[v] = label[ancestor[v]];

    ancestor[v] = ancestor[ancestor[v]];
}
