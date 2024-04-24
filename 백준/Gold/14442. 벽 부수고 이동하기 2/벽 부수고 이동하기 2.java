import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k, map[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][][] visited;

	static int bfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { 0, 0, 0, 1 });
		visited[0][0][0] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			int b = cur[2];
			int d = cur[3];
			if (x == n - 1 && y == m - 1) return d;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isIn(nx, ny)) {
					if (map[nx][ny] == 0 && !visited[b][nx][ny]) {
						queue.offer(new int[] { nx, ny, b, d + 1 });
						visited[b][nx][ny] = true;
					} else if (map[nx][ny] == 1 && b < k && !visited[b + 1][nx][ny]) {
						queue.offer(new int[] { nx, ny, b + 1, d + 1 });
						visited[b + 1][nx][ny] = true;
					}
				}
			}
		}
		return -1;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][];
		visited = new boolean[k + 1][n][m];
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		int answer = bfs();
		System.out.println(answer);
	}
}