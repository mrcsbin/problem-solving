import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[1][i] = dp[1][i - 1] + map[1][i];
        }

        int[] l = new int[m + 2];
        int[] r = new int[m + 2];
        for (int i = 2; i <= n; i++) {
            l[0] = dp[i - 1][1];
            for (int j = 1; j <= m; j++) {
                l[j] = Math.max(l[j - 1], dp[i - 1][j]) + map[i][j];
            }
            r[m + 1] = dp[i - 1][m];
            for (int j = m; j >= 1; j--) {
                r[j] = Math.max(r[j + 1], dp[i - 1][j] ) + map[i][j];
            }

            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(l[j], r[j]);
            }
        }
        System.out.println(dp[n][m]);
    }
}