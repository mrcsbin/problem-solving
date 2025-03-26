import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxSum = Integer.MIN_VALUE;
        int l = 0;
        int sum = 0;
        for (int r = 0; r < n; r++) {
            sum += arr[r];

            if (r - l + 1 == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= arr[l++];
            }
        }

        System.out.println(maxSum);
    }
}