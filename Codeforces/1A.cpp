#include <iostream>

long long n, m, a;

long long check()
{
	long long l, r;
	if (n%a == 0)
		l = n/a;
	else 
		l = (n/a)+1;
	if (m%a == 0)
		r = m/a;
	else
		r = (m/a)+1;
		
	return l*r;
}

/* long long check() 
{
	if (a == 1) // BASE CASE
		return n * m;
		
	else if (n == m) { // SQUARE CASE
		if (a >= m)
			return 1;
		else if (a >= m/2)
			return 4;
		else {
			if (n%a == 0)
				return (n*n)/(a*a);
			else 
				return ((n*n)/(a*a))+1;
		}
	}
	else { // RECTANGLE CASE
		long long n1 = n/a;
		long long m1 = m/a;
		
		long long product = n1*m1;
		if (product == 0 || product == 1)
			return 1;
		else {
			if (n%a == 0 && m%a == 0) 
				return (n*m)/(a*a);
			else {
				if (n%a != 0)
					++n1;
				if (m%a != 0)
					++m1;
				return n1*m1;
			}
		}	
	}
} */

int main()
{
	std::cin >> n >> m >> a;
	
	std::cout << check() << std::endl;
	return 0;
}