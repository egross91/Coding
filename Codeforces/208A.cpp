#include <iostream>
#include <vector>

using namespace std;

bool isWub(const char w, const char u, const char b)
{
	return (w == 'W' && u == 'U' && b == 'B');
}

int main() 
{
	string line;
	cin >> line;
	
	size_t n = line.length();
	vector<string> out;
	for (int i = 0; i < n; ++i) { 
		if (i+2 < n && isWub(line[i], line[i+1], line[i+2])) 
			i += 2;
		else {
			string word;
			int j = i;
			
			while (j+2 < n && !isWub(line[j], line[j+1], line[j+2])) 
				word += line[j++];
			if (n-j == 2) {
				word += line[n-2];
				word += line[n-1];
				j = n;
			}
			else if (n-j == 1) {
				word += line[n-1];
				j = n;
			}	
			out.push_back(word);
			i = j-1;
		}
	}
	
	for (unsigned i = 0; i != out.size(); ++i)
		cout << out[i] << " ";
	
	return 0;
}