import java.io.*;

class Main {
	private static BufferedReader in;
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner(System.in);
		in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < T; ++i) {
			long N = Integer.parseInt(in.readLine());
			long ret = (long)((N*(N+2)*(2*N+1))/8);
			System.out.println(ret);
		}
	}
}