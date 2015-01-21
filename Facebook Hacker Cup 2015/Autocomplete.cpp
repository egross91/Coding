#include <iostream>
#include <cstdio>
#include <unordered_set>
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

/** METHOD PROTOTYPES **/
int process();
int prefixLength(const string&);

/** GLOBALS **/
int T, N;
unordered_set<string>* trie;
ifstream in("autocomplete.in.txt", ifstream::in);
ofstream out("autocomplete.out.txt", ofstream::out);

int main(int argc, char** argv) {
    int caze = 1;
    // scanf("%i", &T);
    in >> T;

    while (T--) {
        // scanf("%i", &N);
        in >> N;
        trie = new unordered_set<string>();

        // printf("Case #%i: %i\n", caze++, process());
        out << "Case #" << caze++ << ": " << process() << endl;
        delete trie;
    }

    return 0;
}

int process() {
    string line;
    int count = 0, length;

    for (int i = 0; i < N; ++i) {
        // cin >> line;
        in >> line;

        length = prefixLength(line);
        count += length;
    }

    return count;
}

int prefixLength(const string& word) {
    int i;
    for (i = 1; i < word.length(); ++i)
        if (trie->insert(word.substr(0, i)).second)
            break;

    int length = i;
    // cout << "len: " << length << " substr: " << word.substr(0, length) << endl;
    while (i <= word.length())
        trie->insert(word.substr(0, ++i));

    return length;
}
