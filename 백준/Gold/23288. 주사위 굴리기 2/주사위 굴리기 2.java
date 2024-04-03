import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k, map[][], dir, cx, cy, answer, score[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dice = { 0, 1, 2, 3, 4, 5, 6 };

	static void setScore() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (score[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
	}

	static void moveDice() {
		int nx = cx + dx[dir];
		int ny = cy + dy[dir];
		if (!isIn(nx, ny)) {
			dir = (dir + 2) % 4;
			nx = cx + dx[dir];
			ny = cy + dy[dir];
		}
		cx = nx;
		cy = ny;
		answer += score[cx][cy];
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}

	static void rollDice() {
		int temp = dice[1];
		if (dir == 0) {
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
		} else if (dir == 1) {
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
		} else if (dir == 2) {
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
		} else if (dir == 3) {
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
		}
	}

	static void bfs(int x, int y) {
		boolean[][] visited = new boolean[n][m];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		int count = 1;
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == map[x][y]) {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					count++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				if (visited[i][j]) {score[i][j] = map[i][j] * count;
			}
		}
	}

	static void changeDir() {
		if (dice[6] > map[cx][cy]) dir = (dir + 1) % 4;
		else if (dice[6] < map[cx][cy]) dir = (4 + dir - 1) % 4;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cx = cy = 0;
		map = new int[n][];
		score = new int[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dir = 1;
		setScore();
		while (k-- > 0) {
			moveDice();
			rollDice();
			changeDir();
		}
		System.out.println(answer);
	}
}