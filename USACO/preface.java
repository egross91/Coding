/*
ID: eric.bg1
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

class preface {
    private static Map<Integer, String> ones      = new HashMap<Integer, String>();
    private static Map<Integer, String> tens      = new HashMap<Integer, String>();
    private static Map<Integer, String> hundreds  = new HashMap<Integer, String>();
    private static Map<Integer, String> thousands = new HashMap<Integer, String>();

    static {
        ones.put(0, "");
        ones.put(1, "I");
        ones.put(2, "II");
        ones.put(3, "III");
        ones.put(4, "IV");
        ones.put(5, "V");
        ones.put(6, "VI");
        ones.put(7, "VII");
        ones.put(8, "VIII");
        ones.put(9, "IX");

        tens.put(0, "");
        tens.put(1, "X");
        tens.put(2, "XX");
        tens.put(3, "XXX");
        tens.put(4, "XL");
        tens.put(5, "L");
        tens.put(6, "LX");
        tens.put(7, "LXX");
        tens.put(8, "LXXX");
        tens.put(9, "XC");

        hundreds.put(0, "");
        hundreds.put(1, "C");
        hundreds.put(2, "CC");
        hundreds.put(3, "CCC");
        hundreds.put(4, "CD");
        hundreds.put(5, "D");
        hundreds.put(6, "DC");
        hundreds.put(7, "DCC");
        hundreds.put(8, "DCCC");
        hundreds.put(9, "CM");

        thousands.put(1, "M");
        thousands.put(2, "MM");
        thousands.put(3, "MMM");
    }

	public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out   = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		
        int value = Integer.parseInt(in.readLine());

        out.print(solve(value));
        out.close();
        System.exit(0);
	}

    private static String solve(int limit) throws Exception {
        List<String> values = new ArrayList<String>();

        for (int i = 1; i <= limit; ++i) {
            values.add(buildNumeralRepresentation(i));
        }

        String numeralCounts = countNumerals(values);
        return sortNumeralCounts(numeralCounts);
    }

    private static String buildNumeralRepresentation(int value) throws Exception {
        StringBuilder builder = new StringBuilder();
        int multiplier        = 1;
        int temp              = value;

        while (temp > 0) {
            int digit = temp % 10;

            switch (multiplier) {
                case 1:    builder.append(ones.get(digit)); break;
                case 10:   builder.append(tens.get(digit)); break;
                case 100:  builder.append(hundreds.get(digit)); break;
                case 1000: builder.append(thousands.get(digit)); break;
                default: throw new Exception("Multiplier cannot be > 1000.");
            }

            multiplier *= 10; // Point to next position in @param:value.
            temp      /= 10; // Reduce @param:value by the digit we already looked at.
        }

        return builder.toString();
    }

    private static String countNumerals(List<String> numerals) {
        Map<Character, Integer> counts = new HashMap<Character, Integer>();

        for (String numeral : numerals) {
            for (int i = 0; i < numeral.length(); ++i) {
                char c        = numeral.charAt(i);
                Integer count = counts.get(c);

                count = ((count == null) ? 1 : (count+1));

                counts.put(c, count);
            }
        }

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            builder.append(String.format("%c %d%n", entry.getKey(), entry.getValue()));
        }

        return builder.toString().trim();
    }

    private static String sortNumeralCounts(String counts) {
        StringBuilder builder = new StringBuilder();
        String[] lines        = counts.split("\n");

        builder.append(findLine(lines, 'I'));
        builder.append(findLine(lines, 'V'));
        builder.append(findLine(lines, 'X'));
        builder.append(findLine(lines, 'L'));
        builder.append(findLine(lines, 'C'));
        builder.append(findLine(lines, 'D'));
        builder.append(findLine(lines, 'M'));

        return builder.toString();
    }

    private static String findLine(String[] lines, char c) {
        for (int i = 0; i < lines.length; ++i) {
            if (lines[i].charAt(0) == c) {
                return String.format("%s%n", lines[i]);
            }
        }

        return "";
    }
}