import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r, c, n, dir;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static List<int[]> list;

	static void throwSpear(int h) {
		if (dir == 0) {
			for (int i = 0; i < c; i++) {
				if (map[h][i] == 'x') {
					map[h][i] = '.';
					break;
				}
			}
		} else {
			for (int i = c - 1; i >= 0; i--) {
				if (map[h][i] == 'x') {
					map[h][i] = '.';
					break;
				}
			}
		}
		dir = (dir + 1) % 2;
		checkCluster();
	}

	static void checkCluster() {
		visited = new boolean[r][c];
		for (int i = 0; i < c; i++) {
			if (!visited[r - 1][i] && map[r - 1][i] == 'x') {
				bfs(r - 1, i);
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!visited[i][j] & map[i][j] == 'x') {
					list.add(new int[] {i, j});
				}
			}
		}
		
		fallCluster();
		
		for (int i = 0; i < r; i++) {
			Arrays.fill(map[i], '.');
		}
		
		for (int[] pos : list) {
			map[pos[0]][pos[1]] = 'x';
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (visited[i][j]) map[i][j] = 'x';
			}
		}
		list.clear();
	}

	static void fallCluster() {
		boolean isValid = true;
		while (isValid && list.size() != 0) {
			for (int[] pos : list) {
				pos[0] += 1;
			}
			for (int[] pos : list) {
				if (pos[0] == r - 1) isValid = false;
				else if (visited[pos[0] + 1][pos[1]]) isValid = false;
			}
		}
	}

	static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			x = cur[0];
			y = cur[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 'x') {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < r && y < c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][];
		list = new ArrayList<>();
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
			int h = Integer.parseInt(st.nextToken());
			throwSpear(r - h);
		}
		
		for (char[] maps : map) {
			for (char p : maps) {
				System.out.print(p);
			}
			System.out.println();
		}
	}
}