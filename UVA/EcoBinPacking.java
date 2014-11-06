import java.util.*;
import java.io.*;

class Main
{
    public static void main(String[] args)
    {
        StringBuilder answers = new StringBuilder();

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int b1, g1, c1, b2, g2, c2, b3, g3, c3;
            while (true)
            {
                String line = in.readLine();
                if (line == null || line.equals("")) break;

                StringTokenizer tokens = new StringTokenizer(line);
                b1 = Integer.parseInt(tokens.nextToken());
                g1 = Integer.parseInt(tokens.nextToken());
                c1 = Integer.parseInt(tokens.nextToken());
                b2 = Integer.parseInt(tokens.nextToken());
                g2 = Integer.parseInt(tokens.nextToken());
                c2 = Integer.parseInt(tokens.nextToken());
                b3 = Integer.parseInt(tokens.nextToken());
                g3 = Integer.parseInt(tokens.nextToken());
                c3 = Integer.parseInt(tokens.nextToken());

                int total = (b1 + g1 + c1 + b2 + g2 + c2 + b3 + g3 + c3);
                String result = "BCG";
                int max = b1 + c2 + g3;

                if (max < b1 + g2 + c3)
                {
                    result = "BGC";
                    max = b1 + g2 + c3;
                }
                if (max < c1 + b2 + g3)
                {
                    result = "CBG";
                    max = c1 + b2 + g3;
                }
                if (max < c1 + g2 + b3)
                {
                    result = "CGB";
                    max = c1 + g2 + b3;
                }
                if (max < g1 + b2 + c3)
                {
                    result = "GBC";
                    max = g1 + b2 + c3;
                }
                if (max < g1 + c2 + b3)
                {
                    result = "GCB";
                    max = g1 + c2 + b3;
                }

                answers.append(String.format("%s %d%n", result, (total-max)));
            }
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        System.out.print(answers);
    }
}