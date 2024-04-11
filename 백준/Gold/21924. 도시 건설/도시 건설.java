import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m, p[];
	static long answer;
	static List<Edge> edgeList;

	static class Edge {
		int s;
		int e;
		int w;

		Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}

	static void kruskal() {
		makeSet();
		int count = 0;
		for (Edge edge : edgeList) {
			if (!union(edge.s, edge.e)) continue;
			answer += edge.w;
			if (++count == n - 1) break;
		}
		if (count != n - 1) answer = -1;
	}

	static void makeSet() {
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
	}

	static int find(int a) {
		if (a == p[a]) return a;
		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		p[b] = a;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		edgeList = new ArrayList<>();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		long sum = 0;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(s, e, w));
			sum += w;
		}
		Collections.sort(edgeList, (o1, o2) -> o1.w - o2.w);
		kruskal();
		System.out.println(answer == -1 ? -1 :sum - answer);
	}
}