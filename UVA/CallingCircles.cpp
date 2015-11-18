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

#define MAX 25

using namespace std;

/** Data Structures & Types **/
typedef vector<bool> B_List;
typedef vector<int> I_List;

// Adjacency matrix
typedef vector<B_List> Graph;

/** Globals **/
int N, M, namePtr, iteration, index;
string names[MAX+1];
int numberPeople[MAX+1];
bool checked[MAX+1];
I_List indices, low_link;
B_List on_stack;

/** Method Decalrations **/
void init();
Graph buildGraph(const int, const int);
void solve(const Graph&);
void search(const Graph&, const int, stack<int>&);
bool detectCycle(const Graph&, const int, const int, bool[MAX][MAX], stack<int>&, set<int>&);
void printComponent(set<int>);

/** Helper methods **/
int getNameIndex(const string&);
string getName(const int);

int main(int argc, char** argv) 
{
	// freopen("CallingCircles.in", "r", stdin);
	iteration = 1;

	scanf("%d %d", &N, &M);
    while (true)
    {
    	if (!N && !M) break;
    	init();
    	Graph G = buildGraph(N, M);
    	solve(G);


    	scanf("%d %d", &N, &M);
    	if (!N && !M) break;
    	printf("\n");
    }

    // fclose(stdout);
    return 0;
}

void init() 
{
	namePtr  = 0;
	index    = 0;
	indices  = I_List(MAX+1, -1);
	low_link = I_List(MAX+1, 1e7);
	on_stack = B_List(MAX+1, false);

	for (int i = 0; i < MAX; ++i)
		checked[i] = false;
}

Graph buildGraph(const int N, const int M)
{
	Graph ret(N+1, B_List(N+1, false));
	char left[1000], right[1000];
	int from, to;

	for (int i = 0; i < M; ++i)
	{
		scanf("%s %s", left, right);
		from          = getNameIndex(string(left));
		to            = getNameIndex(string(right));
		ret[from][to] = true;
	}

	return ret;
}

void solve(const Graph& G)
{
	printf("Calling circles for data set %i:\n", iteration++);
	
	for (int i = 0; i < namePtr; ++i)
	{
		// Only search what isn't already put into a set.
		if (checked[i] == false)
		{
			stack<int> s;
			search(G, i, s);
		}
	}
}

void search(const Graph& G, const int current, stack<int>& s)
{
	indices[current]  = index;
	low_link[current] = index;
	index++;

	s.push(current);
	on_stack[current] = true;

	for (int i = 0; i < N; ++i)
	{
		if (G[current][i] == true)
		{
			if (indices[i] == -1)
			{
				search(G, i, s);
				low_link[current] = min(low_link[i], low_link[current]);
			}
			else if (on_stack[i])
			{
				low_link[current] = min(low_link[current], indices[i]);
			}
		}
	}

	if (low_link[current] == indices[current])
	{
		set<int> component;
		int w = -1;

		while (w != current)
		{
			w = s.top(); s.pop();

			on_stack[w] = false;
			component.insert(w);
		}
		printComponent(component);
	}
}

void printComponent(set<int> s)
{
	set<int>::iterator itr = s.begin();
	printf("%s", getName(*itr).c_str());
	checked[*itr] = true;

	++itr;

	for (; itr != s.end(); ++itr)
	{
		checked[*itr] = true;
		printf(", %s", getName(*itr).c_str());
	}
	printf("\n");
}

int getNameIndex(const string& name)
{
	for (int i = 0; i < namePtr; ++i)
	{
		if (names[i] == name)
			return i;
	}

	// If doesn't exist, put it into the list of names.
	names[namePtr++] = name;
	return namePtr-1;
}

string getName(const int index)
{
	return names[index];
}
