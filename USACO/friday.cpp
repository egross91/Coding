/*
ID: eric.bg1
PROG: friday
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

/** GLOBALS **/
const int days_in_month[] = { 31,28,31,30,31,30,31,31,30,31,30,31 };

/** PUBLIC METHODS **/
bool isLeapYear(const int&);
void countDays(const int&, ofstream&);
void printArray(int*, ofstream&);


int main()
{
	ifstream fin("friday.in");
	ofstream fout("friday.out");
	
	int N;
	fin >> N;
	
	countDays(N, fout);
	
	fin.close();
	fout.close();
	return 0;
}

bool isLeapYear(const int& year)
{
	if ((!(year % 400) || (year % 100)) && !(year % 4)) 
		return true;
	
	return false;
}

void countDays(const int& N, ofstream& fout)
{
	int* day_counts = new int[7];
	
	for (int d = 0; d < 7; ++d)
		day_counts[d] = 0;

	int day = 0; // Saturday Sunday Monday Tuesday Wednesday Thurdsay Friday
	
	for (int year = 1900; year < 1900+N; ++year) { // year
		bool leap = isLeapYear(year);
		for (int month = 0; month < 12; ++month) {
			day_counts[day]++;
			// cout << day << endl;
			if (leap && month == 1)  // February and a Leap Year 
				day = (day + ((days_in_month[month]+1) % 28)) % 7;
			else if (month == 1)
				continue;
			else 
				day = (day + ((days_in_month[month]) % 28)) % 7;
		}
	}
	
	printArray(day_counts, fout);
	
	delete[] day_counts;
}

void printArray(int* day_counts, ofstream& fout)
{
	for (int d = 0; d < 7; ++d) {
		if (d < 6)
			fout << day_counts[d] << " ";
		else 
			fout << day_counts[d] << endl;
	}

}
