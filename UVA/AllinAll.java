import java.util.*;
import java.io.*;

class Main
{
    public static void main(String[] args)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();

        String line;
        try
        {
            while (true)
            {
                line = in.readLine();
                if (line == null || line.equals("")) break;

                answers.append(solve(line.trim().split(" ")));
            }
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        System.out.println(answers.toString().trim());
    }

    private static String solve(String[] data)
    {
        if (data[1].length() < data[0].length())
            return "No\n";

        String s = data[0];
        String t = data[1];

        int sLength = s.length();
        int tLength = t.length();
        int si = 0;
        int ti = 0;

        for (; ti < tLength; ++ti)
        {
            if (s.charAt(si) == t.charAt(ti))
            {
                ++si;

                if (si == sLength)
                    return "Yes\n";
            }
        }

        return "No\n";
    }
}
