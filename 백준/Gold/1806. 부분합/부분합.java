import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int l = 0;
        int answer = Integer.MAX_VALUE;
        for (int r = 0; r < n; r++) {
            sum += arr[r];

            while (sum >= s) {
                answer = Math.min(answer, r - l + 1);
                sum -= arr[l++];
            }
        }


        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}