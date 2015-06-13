import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        int tests = Integer.parseInt(in.readLine());

        while (tests-- != 0) {
            StringBuilder value = new StringBuilder(in.readLine());

            int count = 0;
            while (value.length() != 0) {
                int digit = (int)value.charAt(value.length()-1) - '0';
                if (digit == 0 || digit == 6 || digit == 9) {
                    ++count;
                }
                else if (digit == 8) {
                    count += 2;
                }

                value.setLength(value.length()-1);
            }

            builder.append(String.valueOf(count) + "\n");
        }

        System.out.print(builder);
	}
}
