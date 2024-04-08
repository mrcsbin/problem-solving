import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n, nums[];
	static boolean[] isUsed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		isUsed = new boolean[100001];
		long answer = 0;
		int r = 0;
		for (int l = 0; l < n; l++) {
			while (r < n && !isUsed[nums[r]]) {
				isUsed[nums[r++]] = true;
				answer += r - l;
				continue;
			}
			isUsed[nums[l]] = false;
		}
		System.out.println(answer);
	}
}