import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = Integer.parseInt(br.readLine());

        int left = 1;
        int right = Arrays.stream(arr).max().getAsInt();
        int mid;
        int answer = m;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (canDistribute(mid)) {
                answer = mid;
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }

        System.out.println(answer);
    }

    private static boolean canDistribute(int target) {
        int sum = 0;
        for (int cost : arr) {
            sum += Math.min(cost, target);
        }
        return sum <= m;
    }
}