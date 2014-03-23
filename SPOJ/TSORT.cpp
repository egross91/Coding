#include <cstdio>
#include <sstream>
#include <queue>
#include <vector>

using namespace std;

/** DATA STRUCTURE **/
struct Comp {
	Comp() { };
	bool operator() (const int& left, const int& right) const {
		return (left > right);
	}
};

typedef priority_queue<int, vector<int>, Comp> comp_q;

/** PUBLIC METHODS **/
void buildQueue(comp_q&,const int&);
void goThroughQueue(comp_q&,const int&,ostringstream&);

int main()
{	
	ostringstream ss;
	int N;
	scanf("%d",&N);
	
	comp_q Q;
		
	buildQueue(Q,N);
	goThroughQueue(Q,N,ss);
	
	printf("%s",ss.str().c_str());
	return 0;
}

void buildQueue(comp_q& Q, const int& N)
{
	int x;
	for (int i = 0; i < N; ++i) {
		scanf("%d",&x);
		Q.push(x);
	}
}

void goThroughQueue(comp_q& Q, const int& N, ostringstream& ss)
{
	for (int i = 0; i < N; ++i) {
		ss << Q.top() << "\n";
		Q.pop();
	}
}
