import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c, answer;
	static char[][] map;
	static boolean[] isUsed = new boolean[26];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void dfs(int x, int y, int count) {
		isUsed[map[x][y] - 'A'] = true;
		answer = Math.max(answer, count);
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isIn(nx, ny) && !isUsed[map[nx][ny] - 'A']) {
				dfs(nx, ny, count + 1);
				isUsed[map[nx][ny] - 'A'] = false;
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
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		dfs(0, 0, 1);
		System.out.println(answer);
	}
}