import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = 0;
        int count = 0;
        int sum = 0;

        while (r < n) {
            sum += arr[r];
            while (sum > m && l <= r) sum -= arr[l++];
            if (sum == m) count++;
            r++;
        }

        System.out.println(count);
    }
}