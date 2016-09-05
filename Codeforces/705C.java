import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		String[] lineData = in.readLine().split(" ");
		int n = Integer.parseInt(lineData[0]);
		int q = Integer.parseInt(lineData[1]);

		List<Queue<Integer>> applications = new ArrayList<Queue<Integer>>();
		for (int i = 0; i <= n; ++i)
			applications.add(new LinkedList<Integer>());

		Queue<QueuePointer> allNotifications = new LinkedList<QueuePointer>();
		boolean[] read                       = new boolean[300001];
		int notificationNumber               = 1;
		int unreadNotifications              = 0;
		for (int i = 0; i < q; ++i) {
			lineData = null;
			lineData = in.readLine().split(" ");

			int type = Integer.parseInt(lineData[0]);
			int op   = Integer.parseInt(lineData[1]);

			switch (type) {
				case 1: 
					applications.get(op).add(notificationNumber);
					allNotifications.add(new QueuePointer(op, notificationNumber++));
					++unreadNotifications;
					break;
				case 2: 
					Queue<Integer> queueToEmpty = applications.get(op);
					while (!queueToEmpty.isEmpty()) {
						Integer notification = queueToEmpty.poll();
						read[notification] = true;
						--unreadNotifications;
					}
					break;
				case 3: 
					while (!allNotifications.isEmpty() && allNotifications.peek().notification <= op) {
						QueuePointer qp = allNotifications.poll();

						if (!read[qp.notification]) {
							read[qp.notification] = true;
							applications.get(qp.queue).remove();
							--unreadNotifications;
						}
					}

					break;
				default: break;
			}

			out.append(String.format("%d%n", unreadNotifications));
		}

		System.out.print(out);
	}

	private static class QueuePointer {
		public final int queue;
		public final int notification;

		public QueuePointer(int q, int n) {
			this.queue = q;
			this.notification = n;
		}
	}
}