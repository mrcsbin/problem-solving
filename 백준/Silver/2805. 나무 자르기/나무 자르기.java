import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        trees = new int[n];
        st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > max) {
                max = trees[i];
            }
        }

        int l = 0;
        int r = max;
        long result = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (binarySearch(mid)) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(result);
    }

    public static boolean binarySearch(int x) {
        long sum = 0;

        for (int tree : trees) {
            if (tree > x) {
                sum += tree - x;
            }
        }

        return sum >= m;
    }
}