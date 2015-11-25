import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int d1 = in.nextInt();
        int d2 = in.nextInt();
        int d3 = in.nextInt();

        int min = Integer.MAX_VALUE;
        int poss1 = (d1 + d2 + d3);
        int poss2 = (2 * (d1 + d2));
        int poss3 = (2 * (d2 + d3));
        int poss4 = (2 * (d1 + d3));

        min = Math.min(poss1, min);
        min = Math.min(poss2, min);
        min = Math.min(poss3, min);
        min = Math.min(poss4, min);

        System.out.println(min);
    }
}
