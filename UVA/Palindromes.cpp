#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <algorithm>
#include <utility>
#include <sstream>
#include <fstream>
#include <iomanip>

struct Node {
    int vertex;
    int weight;

    Node() : vertex(0), weight(0) { };
    Node(int _v, int _w) : vertex(_v), weight(_w) { };
};

struct Comparison {
    bool operator()(const Node& n1, const Node& n2) {
        return (n1.weight < n2.weight);
    }
};


bool checkMirror(const std::string&);
bool checkPalin(const std::string&);
char swapChar(const char&);

std::pair<char, char> pairs[] = { std::pair<char, char>('A', 'A'),
                                  std::pair<char, char>('E', '3'),
                                  std::pair<char, char>('H', 'H'),
                                  std::pair<char, char>('I', 'I'),
                                  std::pair<char, char>('J', 'L'),
                                  std::pair<char, char>('L', 'J'),
                                  std::pair<char, char>('M', 'M'),
                                  std::pair<char, char>('O', 'O'),
                                  std::pair<char, char>('S', '2'),
                                  std::pair<char, char>('T', 'T'),
                                  std::pair<char, char>('U', 'U'),
                                  std::pair<char, char>('V', 'V'),
                                  std::pair<char, char>('W', 'W'),
                                  std::pair<char, char>('X', 'X'),
                                  std::pair<char, char>('Y', 'Y'),
                                  std::pair<char, char>('Z', '5'),
                                  std::pair<char, char>('1', '1'),
                                  std::pair<char, char>('2', 'S'),
                                  std::pair<char, char>('3', 'E'),
                                  std::pair<char, char>('5', 'Z'),
                                  std::pair<char, char>('8', '8') };

int main(int argc, char** argv) {
    std::string input;
    std::ostringstream output;

    while (std::cin >> input) {
        bool mirror = checkMirror(input);
        bool palin = checkPalin(input);

        output << input;
        if (mirror && palin)
            output << " -- is a mirrored palindrome.\n";
        else if (mirror)
            output << " -- is a mirrored string.\n";
        else if (palin)
            output << " -- is a regular palindrome.\n";
        else
            output << " -- is not a palindrome.\n";

        output << "\n";
    }

    std::printf("%s", output.str().c_str());

    return 0;
}

bool checkMirror(const std::string& input) {
    std::string temp = input;

    int length = input.length();
    for (int i = 0; i < length; ++i)
    {
        temp[i] = swapChar(temp[i]);
    }

    int hi = length - 1;
    for (int i = 0; i < length; ++i) {
        if (input[i] != temp[hi--])
            return false;
    }

    return true;
}

bool checkPalin(const std::string& input) {
    int mid = input.length() >> 1;

    int hi = input.length() - 1;
    for (int i = 0; i < mid; ++i)
        if (input[i] != input[hi--])
            return false;

    return true;
}

char swapChar(const char& c) {
    for (int i = 0; i < 21; ++i) {
        if (pairs[i].first == c)
            return pairs[i].second;
    }

    return ' ';
}
