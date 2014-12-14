using System;
using System.Collections.Generic;
using System.IO;
class Solution {
    static void Main(String[] args) {
        int tests = Convert.ToInt32(Console.ReadLine());

        for (int i = 0; i < tests; ++i) {
            int N = Convert.ToInt32(Console.ReadLine());
            string[] tokens = Console.ReadLine().Split(' ');
            int[] numbers = new int[N];

            for (int j = 0; j < N; ++j) {
                numbers[j] = Convert.ToInt32(tokens[j]);
            }

            int contiguous = Kadane(numbers);
            int nonContiguous = GetMax(numbers);

            Console.WriteLine(contiguous + " " + nonContiguous);
        }
    }

    private static int Kadane(int[] nums) {
        int maxSoFar, maxHere;
        maxSoFar = maxHere = -1000000000;

        foreach (var value in nums) {
            maxHere = Math.Max(maxHere + value, value);
            maxSoFar = Math.Max(maxSoFar, maxHere);
        }

        return maxSoFar;
    }


    private static int GetMax(int[] nums) {
        int max = 0;
        int negMax = -100001;

        for (int i = 0; i < nums.Length; ++i) {
            if (nums[i] < 0)
                negMax = Math.Max(negMax, nums[i]);
            else
                max += nums[i];
        }

        return (max != 0) ? max : negMax;
    }
}
