import java.util.*;
import java.io.*;

class Main
{
    public static void main(String[] args)
    {
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        StringBuilder answers = new StringBuilder();

        try
        {
            String line;
            while (in.hasNext())
            {
                int n = in.nextInt();

                int[] arr = new int[n];
                for (int i = 0; i < n; ++i)
                    arr[i] = in.nextInt();

                answers.append("Minimum exchange operations : " + solve(arr) + "\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
            System.exit(1);
        }

        System.out.print(answers);
    }

    private static int solve(int[] arr)
    {
        int swaps = 0;

        boolean flag = true;
        while (flag)
        {
            flag = false;

            for (int i = arr.length-1; i > 0; --i)
            {
                if (arr[i] < arr[i-1])
                {
                    flag = true;
                    ++swaps;

                    int temp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = temp;
                }
            }
        }

        return swaps;
    }
}
