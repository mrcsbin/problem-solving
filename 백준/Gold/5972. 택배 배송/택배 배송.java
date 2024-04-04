import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, dist[];
	static List<Node>[] graph;

	static class Node {
		int idx;
		int w;

		Node(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[v].add(new Node(idx, w));
			graph[idx].add(new Node(v, w));
		}
		
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(1);
		dist[1] = 0;
		while (!pq.isEmpty()) {
			int curIdx = pq.poll();
			for (Node nextNode : graph[curIdx]) {
				if (dist[nextNode.idx] > dist[curIdx] + nextNode.w) {
					dist[nextNode.idx]= dist[curIdx] + nextNode.w;
					pq.offer(nextNode.idx);
				}
			}
		}
		System.out.println(dist[n]);
	}
}