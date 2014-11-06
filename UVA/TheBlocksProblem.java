import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
class Main
{
    public static void main(String[] args)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // Scanner in = new Scanner(System.in);
        // StringBuilder answers = new StringBuilder();

        String line;
        try
        {
            int N = Integer.parseInt(in.readLine().trim());

            LinkedList<LinkedList<Integer>> blocks = getNewList(N);

            // print(blocks);

            // in.nextLine();
            while (true)
            {
                line = in.readLine();
                if (line == null || line.contains("quit") || line.equals("")) break;

            	String[] data = formatLine(line.replaceAll("\\s+", " ").trim());

            	performOperation(data, blocks);
            }

            for (int i = 0; i < N; ++i)
            {
                System.out.print(i + ":");

                LinkedList<Integer> list = (LinkedList<Integer>)blocks.get(i);
                int length = list.size();
                for (int j = 0; j < length; ++j)
                {
                    System.out.print(" " + list.get(j));
                }

                System.out.println();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
            System.exit(1);
        }

        // System.out.print(answers.toString());
    }

    public static String[] formatLine(String input)
    {
        StringTokenizer tokens = new StringTokenizer(input);

        String[] ret = new String[4];

        ret[0] = tokens.nextToken();
        ret[1] = tokens.nextToken();
        ret[2] = tokens.nextToken();
        ret[3] = tokens.nextToken();

        return ret;
    }

    // private static void print(LinkedList<LinkedList<Integer>> blocks)
    // {
    //     for (int i = 0; i < blocks.size(); ++i)
    //     {
    //         System.out.print(i + ": ");
    //
    //         LinkedList<Integer> list = blocks.get(i);
    //         int length = list.size();
    //         for (int j = 0; j < length; ++j)
    //         {
    //             System.out.print(list.get(j));
    //             if (j < length - 1)
    //                 System.out.print(" ");
    //         }
    //
    //         System.out.println();
    //     }
    // }

    public static LinkedList<LinkedList<Integer>> getNewList(int N)
    {
        LinkedList<LinkedList<Integer>> ret = new LinkedList<LinkedList<Integer>>();

        for (int i = 0; i < N; ++i)
        {
            LinkedList<Integer> temp = new LinkedList<Integer>();
            temp.add(i);
            ret.add(temp);
        }

        return ret;
    }

    public static void performOperation(String[] data, LinkedList<LinkedList<Integer>> blocks)
    {
        int a = Integer.parseInt(data[1]);
        int b = Integer.parseInt(data[3]);

        Coord coordA = findBlock(a, blocks);
        Coord coordB = findBlock(b, blocks);

        if (coordA.stack == coordB.stack)
            return;

        if (data[0].contains("move"))
        {
            if (data[2].contains("onto"))
                performMoveOnto(coordA, coordB, blocks);
            else
                performMoveOver(coordA, coordB, blocks);
        }
        else
        {
            if (data[2].contains("onto"))
                performPileOnto(coordA, coordB, blocks);
            else
                performPileOver(coordA, coordB, blocks);
        }
    }

    public static void performMoveOnto(Coord a, Coord b, LinkedList<LinkedList<Integer>> blocks)
    {
        onto(a, blocks);
        onto(b, blocks);

        move(a, b, blocks);
    }

    public static void performMoveOver(Coord a, Coord b, LinkedList<LinkedList<Integer>> blocks)
    {
        onto(a, blocks);

        move(a, b, blocks);
    }

    public static void performPileOnto(Coord a, Coord b, LinkedList<LinkedList<Integer>> blocks)
    {
        onto(b, blocks);

        pile(a, b, blocks);
    }

    public static void performPileOver(Coord a, Coord b, LinkedList<LinkedList<Integer>> blocks)
    {
        pile(a, b, blocks);
    }

    public static void onto(Coord coord, LinkedList<LinkedList<Integer>> blocks)
    {
        LinkedList<Integer> list = (LinkedList<Integer>)blocks.get(coord.stack);

        int num = list.peekLast();
        while (true)
        {
            if (num == coord.value)
                break;

            num = (Integer)list.removeLast();

            LinkedList<Integer> block = (LinkedList<Integer>)blocks.get(num);
            block.offerFirst(num);
            num = (Integer)list.peekLast();
        }
    }

    public static void move(Coord a, Coord b, LinkedList<LinkedList<Integer>> blocks)
    {
        LinkedList<Integer> aList = (LinkedList<Integer>)blocks.get(a.stack);
        LinkedList<Integer> bList = (LinkedList<Integer>)blocks.get(b.stack);

        int num = (Integer)aList.removeLast();
        bList.offerLast(num);
    }

    public static void pile(Coord a, Coord b, LinkedList<LinkedList<Integer>> blocks)
    {
        LinkedList<Integer> aList = (LinkedList<Integer>)blocks.get(a.stack);
        LinkedList<Integer> bList = (LinkedList<Integer>)blocks.get(b.stack);

        LinkedList<Integer> xfer = new LinkedList<Integer>();

        boolean flag = false;
        int num = (Integer)aList.peekLast();
        while (num != a.value)
        {
            num = (Integer)aList.removeLast();
            xfer.offerFirst(num);
            num = (Integer)aList.peekLast();
            flag = true;
            if (num == a.value)
            {
                move(a, b, blocks);
                break;
            }
        }

        if (!flag)
        {
            move(a, b, blocks);
        }

        while (xfer.size() != 0)
        {
            num = (Integer)xfer.pollFirst();
            bList.offerLast(num);
        }
    }

    public static Coord findBlock(int value, LinkedList<LinkedList<Integer>> blocks)
    {
        for (int i = 0; i < blocks.size(); ++i)
        {
            LinkedList<Integer> curr = (LinkedList<Integer>)blocks.get(i);

            for (int j = 0; j < curr.size(); ++j)
            {
                if ((Integer)curr.get(j) == value)
                    return new Coord(i, value);
            }
        }

        return null;
    }
}

class Coord
{
    public int stack;
    public int value;

    public Coord()
    {
        this.stack = 0;
        this.value = 0;
    }

    public Coord(int b, int i)
    {
        this.stack = b;
        this.value = i;
    }
}
