import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = n - 1;
        int answer = Integer.MAX_VALUE;
        int al = 0;
        int ar = n - 1;
        while (l < r) {
            int sum = a[l] + a[r];
            if (sum < 0) {
                if (Math.abs(answer) > Math.abs(sum)) {
                    answer = sum;
                    al = a[l];
                    ar = a[r];
                }
                l++;
            } else {
                if (Math.abs(answer) > Math.abs(sum)) {
                    answer = sum;
                    al = a[l];
                    ar = a[r];
                }
                r--;
            }
        }
        System.out.println(al + " " + ar);
    }
}