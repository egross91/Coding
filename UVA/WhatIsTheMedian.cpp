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

int getMedian(std::vector<int>&, const int&);

int main(int argc, char** argv) {
    int value, count = 0;
    std::vector<int> nums;
    std::ostringstream output;

    while (std::scanf("%d", &value) == 1) {
        nums.push_back(value);

        output << getMedian(nums, ++count) << "\n";
    }

    std::printf("%s", output.str().c_str());

    return 0;
}

int getMedian(std::vector<int>& nums, const int& size) {
    int mid = size >> 1;

    std::sort(nums.begin(), nums.end());

    int median = (size & 0x1) ? nums[mid] : ((nums[mid] + nums[mid-1]) >> 1);

    return median;
}
