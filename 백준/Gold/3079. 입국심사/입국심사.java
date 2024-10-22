import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long left = 1;
        long right = 1_000_000_000L * 1_000_000_000L;
        long mid;
        long answer = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (canTest(mid)) {
                answer = mid;
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }
        System.out.println(answer);
    }

    private static boolean canTest(long target) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            count += target / arr[i];
            if (count >= m) break;
        }
        return count >= m;
    }
}