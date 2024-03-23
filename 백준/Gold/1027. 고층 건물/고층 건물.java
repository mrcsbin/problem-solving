import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, height[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        height = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            double slope = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 1; j--) {
                if (slope > getSlope(i, height[i], j, height[j])) {
                    slope = getSlope(i, height[i], j, height[j]);
                    count++;
                }
            }

            slope = Integer.MIN_VALUE;
            for (int j = i + 1; j <= n; j++) {
                if (slope < getSlope(i, height[i], j, height[j])) {
                    slope = getSlope(i, height[i], j, height[j]);
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }

    static double getSlope(int x1, int y1, int x2, int y2) {
        return (double) (y1 - y2) / (x1 - x2);
    }
}