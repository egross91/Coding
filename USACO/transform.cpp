/*
ID: eric.bg1
PROG: transform
LANG: C++
*/
#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

/** DATA STRUCTURE **/
class Transform {
	/// DATA MEMBERS
	const vector<string> matrix;
	
/// METHODS
public:
	Transform(const vector<string> _m) : matrix(_m) { }
	vector<string> getMatrix() const { return matrix; }
	size_t size() { return matrix.size(); }
	
void printMatrix()
{
	for (int r = 0; r < size(); ++r)
		cout << matrix[r] << endl;
}

Transform rotate90() 
{
	const int n = this->size();
	
	string copy;
	for (int i = 0; i < n; ++i) 
		copy.append(this->getMatrix()[i]);
	
	int idx = 0;
	vector<string> test_matrix(n);
	for (int start = ((n*n)-n); start < n*n; ++start) {
		int cur = start;
		while (cur >= 0) {
			test_matrix[idx].push_back(copy[cur]);
			cur -= n;
		}
		++idx;
	}
	
	return Transform(test_matrix);
}

Transform rotate180()
{
	int n = this->size();
	int mid = n / 2;
	int top_r = 0;
	if (!(n & 0x1))
		--mid;
	
	vector<string> test_matrix(this->getMatrix());
	for (int r = n-1; r > mid; --r) {
		int top_c = 0;
		for (int c = n-1; c >= 0; --c) {
			char temp = test_matrix[top_r][top_c];
			test_matrix[top_r][top_c++] = test_matrix[r][c];
			test_matrix[r][c] = temp;
		}
		++top_r;
	}
	
	return Transform(test_matrix);
}

Transform rotate270()
{
	const int n = this->size();
	
	string copy;
	for (int i = 0; i < n; ++i)
		copy.append(this->getMatrix()[i]);
		
	string build;
	int idx = 0;
	vector<string> test_matrix(n);
	for (int start = n-1; start >= 0; --start) {
		int cur = start;
		while (cur < n*n) {
			test_matrix[idx].push_back(copy[cur]);
			cur += n;
		}
		++idx;
	}
	
	return Transform(test_matrix);
}

Transform reflect() /// Across vertical axis
{
	int n = this->size();
	int mid = n / 2;
	// if (!(n & 0x1))
		// --mid;
	
	vector<string> test_matrix(this->getMatrix());
	int right_c = n-1;
	for (int c = 0; c < mid; ++c) {
		for (int r = 0; r < n; ++r) 
			swap(test_matrix[r][c],test_matrix[r][right_c]);
			
		--right_c;
	}
	
	return Transform(test_matrix);
}

/*
Transform reflectVert()
{
	int n = this->size();
	int mid = n / 2;
	
	vector<string> test_matrix(this->getMatrix());
	int bottom = n-1;
	for (int r = 0; r < mid; ++r) {
		for (int c = 0; c < n; ++c) 
			swap(test_matrix[r][c],test_matrix[bottom][c]);
		
		--bottom;
	}
	
	return Transform(test_matrix);
} */

bool combo(const Transform* result)
{
	Transform ref = this->reflect();
	
	if (ref.rotate90() == *result)
		return true;
	if (ref.rotate180() == *result)
		return true;
	if (ref.rotate270() == *result)
		return true;
		
	return false;
}

bool noChange(const Transform* result)
{
	return (*this == *result);
}

/** OPERATOR OVERLOADING **/
bool operator==(const Transform& trans) 
{
	int n = this->size();
	for (int r = 0; r < n; ++r) {
		if (this->getMatrix()[r] != trans.getMatrix()[r])
			return false;
	}
	
	return true;
}
};

/** GLOBALS **/
int N;

/** PUBLIC METHODS **/
Transform* buildMatrix(ifstream& fin)
{
	vector<string> matrix(N);
	for (int i = 0; i < N; ++i) 
		fin >> matrix[i];
	
	return new Transform(matrix);
}

int main()
{
	ifstream fin("transform.in");
	ofstream fout("transform.out");
	
	fin >> N;
	
	Transform* original = buildMatrix(fin);
	const Transform* result = buildMatrix(fin);
	if (original->rotate90() == *result)
		fout << 1 << endl;
	else if (original->rotate180() == *result)
		fout << 2 << endl;
	else if (original->rotate270() == *result)
		fout << 3 << endl;
	else if (original->reflect() == *result)
		fout << 4 << endl;
	else if (original->combo(result))
		fout << 5 << endl;
	else if (original->noChange(result))
		fout << 6 << endl;
	else  /// Invalid Transformation
		fout << 7 << endl;
	
	delete original;
	delete result;
	return 0;
}
