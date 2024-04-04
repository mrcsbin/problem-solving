import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, nums[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        int l = 0;
        int r = 0;
        int answer = Integer.MAX_VALUE;
        while (r < n) {
            if (nums[r] - nums[l] < m) {
                r++;
                continue;
            }
            if (nums[r] - nums[l] == m) {
                answer = m;
                break;
            }
            answer = Math.min(answer, nums[r] - nums[l]);
            l++;
        }
        System.out.println(answer);
    }
}