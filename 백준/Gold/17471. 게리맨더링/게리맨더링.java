import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, p[], answer = Integer.MAX_VALUE;
	static List<Integer>[] adj;
	static boolean[] selected, visited;

	static void divide(int depth) {
		if (depth == n + 1) {
			if (checkConnect()) {
				answer = Math.min(answer, calculateAbs());
			}
			return;
		}

		selected[depth] = true;
		divide(depth + 1);
		selected[depth] = false;
		divide(depth + 1);
	}
	
	static boolean checkConnect() {
		Arrays.fill(visited, false);
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				dfs(i, selected[i]);
				count++;
			}
		}
		return count == 2;
	}
	
	static void dfs(int v, boolean flag) {
		visited[v] = true;
		for (int next : adj[v]) {
			if (selected[next] == flag && !visited[next]) {
				dfs(next, flag);
			}
		}
	}
	
	static int calculateAbs() {
		int abs = 0;
		for (int i = 1; i <= n; i++) {
			if (selected[i]) abs += p[i - 1];
			else abs -= p[i - 1];
		}
		return Math.abs(abs);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		selected = new boolean[n + 1];
		visited = new boolean[n + 1];
		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			while (m-- > 0) {
				int v = Integer.parseInt(st.nextToken());
				adj[i].add(v);
			}
		}
		divide(1);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
}