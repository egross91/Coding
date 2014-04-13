#include <iostream> 
using namespace std;int main(){string input;while(cin>>input){int c=0,i=0;while (i<input.length()){if(input[i]=='T'||input[i]=='L'||input[i]=='F'||input[i]=='D')++c;++i;}cout<<(1<<c)<<endl;}return 0;}
