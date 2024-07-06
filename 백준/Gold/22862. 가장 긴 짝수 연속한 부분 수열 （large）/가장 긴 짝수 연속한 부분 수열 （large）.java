import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = 0;
        int c = 0;
        int answer = Integer.MIN_VALUE;
        while (r < n) {
            if (c < k) {
                if (arr[r] % 2 != 0) c++;
                r++;
                answer = Math.max(answer, r - l - c);
            } else if (arr[r] % 2 == 0) {
                r++;
                answer = Math.max(answer, r - l - c);
            } else {
                if (arr[l] % 2 != 0) c--;
                l++;
            }
        }
        System.out.println(answer);
    }
}