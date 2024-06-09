import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scores = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(scores[1]);
            return;
        }

        dp[0] = 0;
        dp[1] = scores[0] + scores[1];
        dp[2] = Math.max(scores[0] + scores[1], scores[1] + scores[2]);
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 3] + scores[i - 1], dp[i - 2]) + scores[i];
        }

        System.out.println(dp[n]);
    }
}