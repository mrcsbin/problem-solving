import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int l = 0;
        int r = arr.length - 1;
        int sum = 0;
        int minAbs = Integer.MAX_VALUE;
        int val1 = 0;
        int val2 = 0;
        while (l < r) {
            sum = arr[l] + arr[r];
            if (minAbs > Math.abs(sum)) {
                minAbs = Math.abs(sum);
                val1 = arr[l];
                val2 = arr[r];
            } else if (sum > 0) {
                r--;
            } else if (sum < 0) {
                l++;
            } else if (sum == 0) {
                break;
            }
        }

        System.out.println(val1 + " " + val2);
    }
}