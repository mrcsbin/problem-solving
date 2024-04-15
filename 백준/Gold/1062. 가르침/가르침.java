import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, k, answer;
	static String[] strs;
	static boolean[] selected;

	static void dfs(int depth, int start) {
		if (depth == k - 5) {
			int count = getCount();
			answer = Math.max(answer, count);
			return;
		}

		for (int i = start; i < 26; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			dfs(depth + 1, i + 1);
			selected[i] = false;
		}
	}

	static int getCount() {
		int count = strs.length;
		for (String str : strs) {
			for (int i = 0; i < str.length(); i++) {
				if (!selected[str.charAt(i) - 'a']) {
					count--;
					break;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		selected = new boolean[26];
		strs = new String[n];
		answer = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			strs[i] = str.substring(4, str.length() - 4);
		}
		selected['a'- 'a'] = selected['n' - 'a'] = selected['t' - 'a'] = selected['i' - 'a'] = selected['c' - 'a'] = true;
		dfs(0, 0);
		System.out.println(answer == Integer.MIN_VALUE ? 0 : answer);
	}
}