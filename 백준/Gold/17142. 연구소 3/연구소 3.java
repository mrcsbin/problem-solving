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
	static int n, m, map[][], tempMap[][], answer;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Virus> vList;
	static Virus[] vSelected;
	static boolean[][] visited;

	static class Virus {
		int x;
		int y;

		Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][];
		answer = Integer.MAX_VALUE;
		vList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 2) vList.add(new Virus(i, j));
			}
		}
		vSelected = new Virus[m];
		activeVirus(0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static void activeVirus(int depth, int start) {
		if (depth == m) {
			tempMap = new int[n][n];
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				tempMap[i] = map[i].clone();
			}
			int time = findMinTime();
			if (time != -1) {
				answer = Math.min(answer, time); 
			}
			return;
		}

		for (int i = start; i < vList.size(); i++) {
			vSelected[depth] = vList.get(i);
			activeVirus(depth + 1, i + 1);
		}
	}

	static boolean check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tempMap[i][j] == 0) return false;
			}
		}
		return true;
	}

	static int findMinTime() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (Virus virus : vSelected) {
			queue.offer(new int[] { virus.x, virus.y });
			tempMap[virus.x][virus.y] = 1;
		}
		int time = 0;
		while (!queue.isEmpty()) {
			if (check()) {
				return time;
			}
			for (int i = 0, size = queue.size(); i < size; i++) {
				int[] cur = queue.poll();
				for (int j = 0; j < 4; j++) {
					int nx = cur[0] + dx[j];
					int ny = cur[1] + dy[j];
					if (isIn(nx, ny) && !visited[nx][ny] && tempMap[nx][ny] != 1) {
						queue.offer(new int[] { nx, ny });
						visited[nx][ny] = true;
						tempMap[nx][ny] = 1;
					}
				}
			}
			time++;
		}
		return -1;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}
}