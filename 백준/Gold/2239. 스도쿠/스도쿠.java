import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean flag;

	static void dfs(int depth) {
		if (flag)
			return;
		if (depth == 81) {
			for (int[] m : map) {
				for (int x : m) {
					System.out.print(x);
				}
				System.out.println();
			}
			flag = true;
			return;
		}

		int r = depth / 9;
		int c = depth % 9;
		if (map[r][c] != 0) {
			dfs(depth + 1);
		} else {
			for (int i = 1; i <= 9; i++) {
				if (check(r, c, i)) {
					map[r][c] = i;
					dfs(depth + 1);
					map[r][c] = 0;
				}
			}
		}
	}

	static boolean check(int r, int c, int n) {
		for (int i = 0; i < 9; i++) {
			if (n == map[i][c]) return false;
		}

		for (int i = 0; i < 9; i++) {
			if (n == map[r][i]) return false;
		}

		for (int i = ((r / 3) * 3); i < ((r / 3) * 3) + 3; i++) {
			for (int j = ((c / 3) * 3); j < ((c / 3) * 3) + 3; j++) {
				if (n == map[i][j]) return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		dfs(0);
	}
}