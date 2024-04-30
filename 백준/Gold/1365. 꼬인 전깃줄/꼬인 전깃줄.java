import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, arr[], lis[], len;

	static int lowerBound(int s, int e, int v) {
		int mid;
		while (s < e) {
			mid = s + (e - s) / 2;
			if (lis[mid] >= v) e = mid;
			else s = mid + 1;
		}
		return e;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		lis = new int[n];
		for (int i = 0; i < n; i++) {
			if (lis[len] < arr[i]) lis[++len] = arr[i];
			else lis[lowerBound(0, len, arr[i])] = arr[i];
		}
		System.out.println(n - len);
	}
}