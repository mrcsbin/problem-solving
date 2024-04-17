import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, cup[][];
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cup = new int[n][2];
		pq = new PriorityQueue<>();
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cup[i][0] = Integer.parseInt(st.nextToken());
			cup[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cup, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

		for (int i = 0; i < n; i++) {
			if (pq.isEmpty() || pq.size() < cup[i][0]) {
				pq.offer(cup[i][1]);
				continue;
			}
			if (pq.peek() < cup[i][1]) {
				pq.poll();
				pq.offer(cup[i][1]);
			}
		}

		int sum = 0;
		while (!pq.isEmpty()) {
			sum += pq.poll();
		}
		System.out.println(sum);
	}
}