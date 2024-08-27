import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(a);

        int l = 0;
        int r = n - 1;
        int answer = Integer.MAX_VALUE;
        while (l < r) {
            int sum = a[r] + a[l];
            if (Math.abs(answer) >= Math.abs(sum)) answer = sum;
            
            if (sum > 0) r--;
            else l++;
        }
        System.out.println(answer);
    }
}