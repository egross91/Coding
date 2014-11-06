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

                int n = Integer.parseInt(line.trim());

                int result = (n % 9);
                answers.append((result == 0) ? 9 : result);
                answers.append("\n");
            }
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        System.out.println(answers.toString().trim());
    }
}
