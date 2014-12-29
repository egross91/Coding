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

map<char, int> turns = { { 'R', 1 },
                         { 'L', -1 } };

map<char, int> charToEnum = { { 'N', 0 },
                              { 'E', 1 },
                              { 'S', 2 },
                              { 'W', 3 } };

map<int, char> enumToChar = { { 0, 'N' },
                              { 1, 'E' },
                              { 2, 'S' },
                              { 3, 'W' } };

pair<int, int> moves[4] = { make_pair(0, 1),
                            make_pair(1, 0),
                            make_pair(0, -1),
                            make_pair(-1, 0) };

enum directions {
    N = 0,
    E = 1,
    S = 2,
    W = 3
};

bool process(const int&, const int&, int&, int&, char&, vector<vector<bool> >&);

int main(int argc, char** argv) {
    int M, N;
    cin >> M >> N;

    int x, y;
    char orientation;
    vector<vector<bool> > offs(M+1, vector<bool>(N+1, false));
    while (cin >> x >> y >> orientation) {
        bool lost = process(M, N, x, y, orientation, offs);

        cout << x << " " << y << " " << orientation;
        if (lost)
            cout << " LOST";
        cout << endl;
    }

    return 0;
}

bool process(const int& M, const int& N,
             int& x, int& y, char& orientation, vector<vector<bool> >& offs) {
    string line; cin >> line;

    directions direction = (directions)charToEnum[orientation];
    pair<int, int> move = moves[(int)direction];
    char current;
    for (int i = 0; i < line.size(); ++i) {
        current = line[i];
        if (current == 'F') {
            x += move.first;
            y += move.second;

            if (x > M || y > N || x < 0 || y < 0) {
                    x -= move.first;
                    y -= move.second;
                    if (offs[x][y])
                        continue;

                    offs[x][y] = true;
                    orientation = (char)enumToChar[direction];
                    return true;
            }
        }
        else {
            int value = (int)direction;
            value = (value + turns[current]);
            value = (value < 0) ? 3 : (value > 3) ? 0 : value;

            direction = (directions)value;
            move = moves[value];
        }
    }

    // Not lost
    orientation = (char)enumToChar[direction];
    return false;
}
