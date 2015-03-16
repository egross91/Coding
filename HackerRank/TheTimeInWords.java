import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    private static int H, M;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        H = in.nextInt();
        M = in.nextInt();

        String answer = solve();

        System.out.println(answer);
    }

    private static String solve() {
        if (M == 0) {
            return getOclock();
        }
        else if (M == 30) {
            return halfPast();
        }
        else if (M%15 == 0) {
            return getQuarter();
        }
        else if (M < 30) {
            return getPast();
        }
        else {
            return getTo();
        }
    }

    private static String getOclock() {
        return getTwelveBase() + " o' clock";
    }

    private static String halfPast() {
        return "half past " + getTwelveBase();
    }

    private static String getTwelveBase() {
        switch (H) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 10:
                return "ten";
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            default:
                throw new IllegalArgumentException("ya fuckng up hours");
        }
    }

    private static String getQuarter() {
        switch (M) {
            case 15:
                return "quarter past " + getTwelveBase();
            case 45:
                H = (H%12) + 1;
                return "quarter to " + getTwelveBase();
            default:
                throw new IllegalArgumentException("q: you fuck");
        }
    }

    private static String getPast() {
        switch (M) {
            case 1:
                return "one minute past " + getTwelveBase();
            case 2:
                return "two minutes past " + getTwelveBase();
            case 3:
                return "three minutes past " + getTwelveBase();
            case 4:
                return "four minutes past " + getTwelveBase();
            case 5:
                return "five minutes past " + getTwelveBase();
            case 6:
                return "six minutes past " + getTwelveBase();
            case 7:
                return "seven minutes past " + getTwelveBase();
            case 8:
                return "eight minutes past " + getTwelveBase();
            case 9:
                return "nine minutes past " + getTwelveBase();
            case 10:
                return "ten minutes past " + getTwelveBase();
            case 11:
                return "eleven minutes past " + getTwelveBase();
            case 12:
                return "twelve minutes past " + getTwelveBase();
            case 13:
                return "thirteen minutes past " + getTwelveBase();
            case 14:
                return "fourteen minutes past " + getTwelveBase();
            case 16:
                return "sixteen minutes past " + getTwelveBase();
            case 17:
                return "seventeen minutes past " + getTwelveBase();
            case 18:
                return "eighteen minutes past " + getTwelveBase();
            case 19:
                return "nineteen minutes past " + getTwelveBase();
            case 20:
                return "twenty minutes past " + getTwelveBase();
            case 21:
                return "twenty one minutes past " + getTwelveBase();
            case 22:
                return "twenty two minutes past " + getTwelveBase();
            case 23:
                return "twenty three minutes past " + getTwelveBase();
            case 24:
                return "twenty four minutes past " + getTwelveBase();
            case 25:
                return "twenty five minutes past " + getTwelveBase();
            case 26:
                return "twenty six minutes past " + getTwelveBase();
            case 27:
                return "twenty seven minutes past " + getTwelveBase();
            case 28:
                return "twenty eight minutes past " + getTwelveBase();
            case 29:
                return "twenty nine minutes past " + getTwelveBase();
            default:
                    throw new IllegalArgumentException("past: ya fucking up");
        }
    }

    private static String getTo() {
        H = (H % 12) + 1;
        switch (M) {
            case 31:
                return "twenty nine minutes to " + getTwelveBase();
            case 32:
                return "twenty eight minutes to " + getTwelveBase();
            case 33:
                return "twenty seven minutes to " + getTwelveBase();
            case 34:
                return "twenty six minutes to " + getTwelveBase();
            case 35:
                return "twenty five minutes to " + getTwelveBase();
            case 36:
                return "twenty four minutes to " + getTwelveBase();
            case 37:
                return "twenty three minutes to " + getTwelveBase();
            case 38:
                return "twenty two minutes to " + getTwelveBase();
            case 39:
                return "twenty one minutes to " + getTwelveBase();
            case 40:
                return "twenty minutes to " + getTwelveBase();
            case 41:
                return "nineteen minutes to " + getTwelveBase();
            case 42:
                return "eighteen minutes to " + getTwelveBase();
            case 43:
                return "seventeen minutes to " + getTwelveBase();
            case 44:
                return "sixteen minutes to " + getTwelveBase();
            case 46:
                return "fourteen minutes to " + getTwelveBase();
            case 47:
                return "thirteen minutes to " + getTwelveBase();
            case 48:
                return "twelve minutes to " + getTwelveBase();
            case 49:
                return "eleven minutes to " + getTwelveBase();
            case 50:
                return "ten minutes to " + getTwelveBase();
            case 51:
                return "nine minutes to " + getTwelveBase();
            case 52:
                return "eight minutes to " + getTwelveBase();
            case 53:
                return "seven minutes to " + getTwelveBase();
            case 54:
                return "six minutes to " + getTwelveBase();
            case 55:
                return "five minutes to " + getTwelveBase();
            case 56:
                return "four minutes to " + getTwelveBase();
            case 57:
                return "three minutes to " + getTwelveBase();
            case 58:
                return "two minutes to " + getTwelveBase();
            case 59:
                return "one minute to " + getTwelveBase();
            default:
                throw new IllegalArgumentException("past: ya fucking up");
        }
    }
}
