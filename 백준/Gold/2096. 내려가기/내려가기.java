import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] scores = new int[n + 1][3];
        int[][] maxDp = new int[n + 1][3];
        int[][] minDp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 1; i <= n; i++) {
            maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + scores[i][0];
            maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2])) + scores[i][1];
            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + scores[i][2];
            minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + scores[i][0];
            minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2])) + scores[i][1];
            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + scores[i][2];
        }

        int max = Math.max(maxDp[n][0], Math.max(maxDp[n][1], maxDp[n][2]));
        int min = Math.min(minDp[n][0], Math.min(minDp[n][1], minDp[n][2]));
        System.out.println(max + " " + min);
    }
}