import java.util.*;
import java.io.*;

class Main
{
    public static void main(String[] args)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();

        try
        {
            String line;
            int n = Integer.parseInt(in.readLine().trim());
            while (n-- != 0)
            {
                line = in.readLine();
                // if (line == null || line.equals("")) break;

                answers.append(solve(line.trim()));
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

    public static String solve(String input)
    {
        LinkedList<Character> list = new LinkedList<Character>();

        int iLength = input.length();
        char last = ' ';
        try
        {
            for (int i = 0; i < iLength; ++i)
            {
                char c = input.charAt(i);
                if (c == '(' || c == '[')
                    list.push(c);
                else
                {
                    if (c == ']' && (last == '(' || last == ' '))
                        return "No\n";
                    else if (c == ')' && (last == '[' || last == ' '))
                        return "No\n";
                    else
                    {
                        if (c == ']')
                            list.remove(list.indexOf('['));
                        else
                            list.remove(list.indexOf('('));
                    }
                }

                last = c;
            }
        }
        catch (Exception e)
        {
            // System.out.println(e);
            // e.printStackTrace();
            return "No\n";
        }

        if (list.size() != 0)
            return "No\n";
        else
            return "Yes\n";
    }
}
