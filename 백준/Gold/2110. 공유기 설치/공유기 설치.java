import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int l = 1;
        int r = houses[n - 1] - houses[0];
        int mid;
        int answer = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (binarySearch(mid)) {
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean binarySearch(int t) {
        int count = 1;
        int x = houses[0];

        for (int i = 1; i < n; i++) {
            if (houses[i] - x >= t) {
                count++;
                x = houses[i];
            }
        }

        return count >= c;
    }
}