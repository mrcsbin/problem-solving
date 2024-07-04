import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int l = 0;
            int r = n - 1;
            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum == num) {
                    if (l == i) l++;
                    else if (r == i) r--;
                    else {
                        answer++;
                        break;
                    }
                }
                if (sum < num) l++;
                else if (sum > num) r--;
            }
        }
        System.out.println(answer);
    }
}