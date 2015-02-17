import java.util.Scanner;

public class B385 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int c = input.nextInt();
		
		int currentDay = input.nextInt();
		int max = 0;
		for (int i = 0; i < n-1; ++i) {
			int nextDay = input.nextInt();
			if (currentDay - nextDay - c > max)
				max = (currentDay - nextDay - c);
			currentDay = nextDay;
		}
		
		
		System.out.printf("%d", max);
	}
}