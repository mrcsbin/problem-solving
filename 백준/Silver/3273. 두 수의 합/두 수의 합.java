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

        int x = Integer.parseInt(br.readLine());

        int l = 0;
        int r = n - 1;
        int sum = 0;
        int count = 0;
        while (l < r) {
            sum = arr[l] + arr[r];
            if (sum > x) {
                r--;
            } else if (sum < x) {
                l++;
            } else if (sum == x) {
                count++;
                l++;
                r--;
            }
        }

        System.out.println(count);
    }
}