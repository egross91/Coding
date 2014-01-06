#include <iostream>
#include <vector>

std::vector<int> s[50], w[17];
bool visited[50] = {false};

int DFS(const int curr, const int group)
{
	w[group].push_back(curr);
	visited[curr] = true;
	for (unsigned i = 0; i != s[x].size(); ++i) {
		int vrtx = s[curr][i];
		if (visited[vrtx] == false)
			DFS(vrtx, group);
	}
}

int main() 
{
	int n, m;
	std::cin >> n >> m;
	// Read in data
	for (unsigned i = 0; i != m; ++i) {
		int a, b;
		std::cin >> a >> b;
		// Create edges
		s[a].push_back(b);
		s[b].push_back(a);
	}
	
	// DFS
	unsigned group = 0;
	for (unsigned i = 1; i <= n; ++i) {
		// If we reach a Node that has not been put into a group, make a new group
		if (s[i].size() && visited[i] == false) {
			DFS(i, group);
			++group;
		}
	}
	// Too many groups
	if (group > (n/3)) {
		std::cout << -1 << std::endl;
		return 0;
	}
	// Make sure none of the groups are too big as well
	for (unsigned i = 0; i <= (n/3); ++i) {
		if (w[i].size() > 3) {
			std::cout << -1 << std::endl;
			return 0;
		}
	}
	// See if all the groups are of 3
	group = 0;
	for (unsigned i = 1; i <= n; ++i) {
		if (visited[i] == false) {
			while (winners[group].size() == 3)
				++group;
			w[group].push_back(i);
			visited[i] = true;
		}
	}
	// Print the groups
	for (unsigned i = 0; i != (n/3); ++i) {
		for (unsigned j = 0; j != w[i].size(); ++j)
			std::cout << w[i][j] << " ";
		std::cout << std::endl;
	}
	
	return 0;
}