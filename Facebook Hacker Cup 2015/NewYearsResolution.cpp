#include <iostream>
#include <cstdio>
#include <cstring>
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

struct Food {
    int protein, carb, fat;

    Food() : protein(0), carb(0), fat(0) { };
    Food(int _p, int _c, int _f) : protein(_p), carb(_c), fat(_f) { };
};

ifstream in("new_years_resolution.in.txt", ifstream::in);
ofstream out("new_years_resolutions.out.txt", ofstream::out);

string process(vector<Food>);
bool target(vector<int>, int);

int T, GP, GC, GF, N;

int main(int argc, char** argv) {
    in >> T;
    // cin >> T;

    for (int test = 1; test <= T; ++test) {
        in >> GP >> GC >> GF;
        // cin >> P >> C >> F;
        in >> N;
        // cin >> N;

        vector<Food> foods(N);
        for (int i = 0; i < N; ++i)
            in >> foods[i].protein >> foods[i].carb >> foods[i].fat;

        out << "Case #" << test << ": " << process(foods) << endl;
    }

    return 0;
}

string process(vector<Food> foods) {
    int limit, pro, carbs, fat;
    vector<Food> previousFoods;
    previousFoods.push_back(Food(0, 0, 0));

    for (int i = 0; i < N; ++i) {
        Food current = foods[i];

        limit = previousFoods.size();
        for (int j = 0; j < limit; ++j) {
            Food previous = previousFoods[j];
            pro = current.protein+previous.protein;
            carbs = current.carb+previous.carb;
            fat = current.fat+previous.fat;

            if ((pro <= GP) && (carbs <= GC) && (fat <= GF))
                previousFoods.push_back(Food(pro, carbs, fat));
        }
    }

    bool ok = false;
    for (int i = 0; i < previousFoods.size(); ++i) {
        Food food = previousFoods[i];

        if (food.protein == GP && food.carb == GC && food.fat == GF) {
            ok = true;
            break;
        }
    }

    return (ok) ? "yes" : "no";
}
