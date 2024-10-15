import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int l = 1;
        int r = arr[n - 1];
        int answer = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (isFind(mid)) {
                answer = mid;
                l = mid + 1;
                continue;
            }
            r = mid - 1;
        }

        System.out.println(answer);
    }

    private static boolean isFind(int x) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += arr[i] / x;
        }
        
        return count >= m;
    }
}