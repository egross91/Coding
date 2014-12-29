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

class UnionFind {
private:
    vector<int> parent;
    size_t graphs;

public:
    UnionFind(int n) {
        for (int i = 0; i <= n; ++i)
            parent.push_back(-1);

        graphs = n+1;
    }

    int find(int x) {
        if (parent[x] == -1)
            return x;

        return parent[x] = find(parent[x]);
    }

    void merge(int x, int y) {
        int pX = find(x);
        int pY = find(y);

        if (pX != pY) {
            --graphs;
            parent[pX] = pY;
        }
    }

    size_t numGraphs() {
        return graphs;
    }
};

int getChar(const string&, char&, int);

int main(int argc, char** argv) {
    int T;
    char size;
    string line;
    scanf("%i", &T);

    ostringstream output;
    getline(cin, line); // get rid of dangling \r\n
    getline(cin, line); // get rid of black line for first input
    while (T--) {
        getline(cin, line);
        getChar(line, size, 0);

        UnionFind UF((int)(size - 'A'));
        char x, y;
        while (getline(cin, line) && !line.empty()) {
            int index = getChar(line, x, 0);
            getChar(line, y, index);
            UF.merge((int)x-'A', (int)y-'A');
        }

        output << UF.numGraphs();

        if (T != 0)
            output << "\n\n";
    }

    cout << output.str() << endl;

    return 0;
}

int getChar(const string& line, char& ch, int index) {
    while (line[index++] == ' ') {
        // nada
    }

    ch = line[index-1];
    return index;
}
