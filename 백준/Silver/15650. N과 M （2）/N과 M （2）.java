import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int n, m;
	static int[] arr;

	static void recursive(int depth, int start) {
		if (depth == m) {
			for (int x : arr) {
				System.out.print(x + " ");
			}
			System.out.println();
			return;
		}

		for (int i = start; i <= n; i++) {
			arr[depth] = i;
			recursive(depth + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		recursive(0, 1);
	}
}