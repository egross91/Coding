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
                if (line == null || line.equals("0") || line.equals("")) break;

                long n = Long.parseLong(line.trim());

                double result = Math.sqrt(n);

                if (Math.ceil(result) == Math.floor(result))
                    answers.append("yes\n");
                else
                    answers.append("no\n");
            }
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        System.out.println(answers.toString().trim());
    }
}
