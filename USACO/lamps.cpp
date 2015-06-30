/*
ID: eric.bg1
LANG: C++
TASK: lamps
*/
#include <iostream>
#include <cstdio>
#include <vector>
#include <deque>
#include <queue>
#include <stack>
#include <map>
#include <set>
#include <list>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <algorithm>

using namespace std;

struct State {
	vector<bool> lamps;
	int count;

	State() : lamps(), count() { }
	State(vector<bool> l, int c) : lamps(l), count(c) { }

	bool operator< (const State& st) const {
		if (this->lamps < st.lamps) {
			return true;
		}
		else if (this->lamps == st.lamps && 
			     this->count < st.count) {
			return true;
		}

		return false;
	}
};

int N, C, ON_COUNT = 0, OFF_COUNT = 0;
int ON[105], OFF[105];

void solve();
void buildList(int[105], int&, string);
set<vector<bool> > check(set<State>);
bool meetsRequirements(const vector<bool>&);
int strToInt(string);
string intToStr(int);
string listToString(const vector<bool>);
string stringify(const State&);
string reverse(string);
vector<string> split(string, const char);
void _split(string, const char, vector<string>&);

int main(int argc, char** argv) {
    freopen("lamps.in", "r", stdin);
    freopen("lamps.out", "w", stdout);

    string data;
    getline(cin, data);
    N = strToInt(data);

    getline(cin, data);
    C = strToInt(data);

    getline(cin, data);
    buildList(ON, ON_COUNT, data);

    getline(cin, data);
    buildList(OFF, OFF_COUNT, data);

    solve();

    fclose(stdin);
    fclose(stdout);

    return 0;
}

void solve() {
	int k_interval;
	string stateStr;
	set<State> possibilities;
	queue<State> Q;
	set<string> memo;
	Q.push(State(vector<bool>(N+1, true), 0));

	State current, temp;
	while (!Q.empty()) {
		current = Q.front(); Q.pop();
		if (current.count < C) {
			possibilities.insert(current);
		}
		else if (current.count == C) {
			possibilities.insert(current);
			continue;
		}
		else if (current.count > C) {
			continue;
		}

		stateStr = stringify(current);
		if (memo.insert(stateStr).second) {
			temp = current;

			// Button 1.
			for (int i = 1; i <= N; ++i) {
				temp.lamps[i] = !temp.lamps[i];
			}

			if (temp.count+1 <= C) {
				temp.count++;
				Q.push(temp);
			}

			temp = current;

			// Button 2.
			for (int i = 1; i <= N; i += 2) {
				temp.lamps[i] = !temp.lamps[i];
			}

			if (temp.count+1 <= C) {
				temp.count++;
				Q.push(temp);
			}

			temp = current;

			// Button 3.
			for (int i = 2; i <= N; i += 2) {
				temp.lamps[i] = !temp.lamps[i];
			}

			if (temp.count+1 <= C) {
				temp.count++;
				Q.push(temp);
			}

			temp = current;

			// Button 4.
			for (int i = 0; (3*i)+1 <= N; ++i) {
				k_interval = (3*i)+1;
				temp.lamps[k_interval] = !temp.lamps[k_interval];
			}

			if (temp.count+1 <= C) {
				temp.count++;
				Q.push(temp);
			}
		}
	}

	set<vector<bool> > winners = check(possibilities);

	if (winners.size() == 0) {
		printf("IMPOSSIBLE\n");
		return;
	}

	for (set<vector<bool> >::iterator itr = winners.begin(); itr != winners.end(); ++itr) {
		printf("%s\n", listToString(*itr).c_str());
	}
}

set<vector<bool> > check(set<State> possibilities) {
	set<vector<bool> > result;

	for (set<State>::iterator itr = possibilities.begin(); itr != possibilities.end(); ++itr) {
		State current = *itr;
		if (meetsRequirements(current.lamps) && 
		    (current.count == C || C >= (current.count+2))) {
			result.insert(current.lamps);
		}
	}

	return result;
}

bool meetsRequirements(const vector<bool>& candidate) {
	for (int i = 0; i < ON_COUNT; ++i) {
		if (candidate[ON[i]] != true) {
			return false;
		}
	}

	for (int i = 0; i < OFF_COUNT; ++i) {
		if (candidate[OFF[i]] != false) {
			return false;
		}
	}

	return true;
}

void buildList(int toBuild[100], int& count, string data) {
	vector<string> values = split(data, ' ');
	int val;

	for (int i = 0; i < values.size()-1; ++i) {
		val = strToInt(values[i]);
		if (val > 0) {
			toBuild[i] = val;
			++count;
		}
	}
}

int strToInt(string str) {
	int value = 0;
	int multiplier = 1;
	const int length = str.length();

	for (int i = length-1; i >= 0; --i) {
		if (str[i] >= '0' && str[i] <= '9') {
			value += (multiplier * (static_cast<int>(str[i] - '0')));
			multiplier *= 10;
		}
	}

	return value;
}

string intToStr(int value) {
	string result;
	char digit;

	while (value > 0) {

		digit = static_cast<char>((value % 10) + '0');
		result.push_back(digit);

		value /= 10;
	}

	return reverse(result);
}

string listToString(const vector<bool> yee) {
	string result;

	for (int i = 1; i <= N; ++i) {
		result.push_back(static_cast<char>(yee[i] + '0'));
	}

	return result;
}

string stringify(const State& state) {
	string result;

	result.append(listToString(state.lamps));
	// result.append(intToStr(state.count));

	return result;
}

string reverse(string data) {
	char temp;
	int lo = 0, hi = data.length()-1;

	while (lo < hi) {
		temp = data[lo];
		data[lo++] = data[hi];
		data[hi--] = temp;
	}

	return data;
}

vector<string> split(string str, const char delim) {
	vector<string> tokens;
	_split(str, delim, tokens);

	return tokens;
}

void _split(string str, const char delim, vector<string>& tokens) {
	stringstream ss(str);
	string token;

	while (getline(ss, token, delim)) {
		if (!token.empty()) {
			tokens.push_back(token);
		}
	}
}
