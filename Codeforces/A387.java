import java.util.Scanner;

public class A387 {
	public static void run() {
		Scanner input = new Scanner(System.in);
		
		
		String[] firstTime = input.nextLine().split(":");
		String[] secondTime = input.nextLine().split(":");

		int firstHour = Integer.parseInt(firstTime[0],10);
		int firstMinutes = Integer.parseInt(firstTime[1],10);
		
		int secondHour = Integer.parseInt(secondTime[0],10);;
		int secondMinutes = Integer.parseInt(secondTime[1],10);
		
		
		String pMinutes;
		int printMins = firstMinutes - secondMinutes;
		boolean remainder = false;
		if (printMins == 0)
			pMinutes = "00";
		else if (printMins < 0) {
			printMins = 60 + (printMins);
			
			if (printMins < 10) 
				pMinutes = "0" + Integer.toString(printMins);
			else 
				pMinutes = Integer.toString(printMins);
				
			remainder = true;
		}
		else if (printMins < 10) 
			pMinutes = "0" + Integer.toString(printMins);
		else
			pMinutes = Integer.toString(printMins);
		
		
		String pHour;
		int printHour = firstHour - secondHour;
		if (remainder)
			--printHour;
			
		if (printHour == 0)
			pHour = "00";
		else if (printHour < 0) {
			printHour = 24 + (printHour);
			if (printHour < 10) 
				pHour = "0" + Integer.toString(printHour);
			else
				pHour = Integer.toString(printHour);
		}
		else if (printHour < 10) 
			pHour = "0" + Integer.toString(printHour);
		else
			pHour = Integer.toString(printHour);
		
		
		
		String ret = pHour + ":" + pMinutes;
		System.out.printf("%s", ret);
	}
	
	
	public static void main(String[] args) {
		A387 A = new A387();
		A.run();
	}
}


	