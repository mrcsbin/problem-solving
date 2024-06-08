import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k, n, arr[];

    private static boolean isValid(long target) {
        long count = 0;
        for (int length : arr) {
            count += length / target;
        }
        return count >= n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[k];

        int max = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long start = 1;
        long end = max;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (isValid(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }
}
