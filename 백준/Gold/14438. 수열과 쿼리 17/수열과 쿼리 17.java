import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int tree[], arr[], n, m;

	static int init(int node, int start, int end) {
		if (start == end) return tree[node] = arr[start];
		int mid = (start + end) / 2;
		return tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
	}

	static int update(int node, int start, int end, int index) {
		if (index < start || end < index) return tree[node];
		if (start == end) return tree[node] = arr[index];
		int mid = (start + end) / 2;
		return tree[node] = Math.min(update(node * 2, start, mid, index), update(node * 2 + 1, mid + 1, end, index));
	}

	static int query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) return Integer.MAX_VALUE;
		if (left <= start && end <= right) return tree[node];
		int mid = (start + end) / 2;
		return Math.min(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		int h = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
		tree = new int[1 << (h + 1)];
		init(1, 1, n);
		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (q == 1) {
				arr[i] = v;
				update(1, 1, n, i);
			} else {
				sb.append(query(1, 1, n, i, v)).append("\n");
			}
		}
		System.out.println(sb);
	}
}