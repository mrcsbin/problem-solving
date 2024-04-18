import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, num[], a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n == 1 || (n == 2 && num[1] != num[0])) {
            System.out.println("A");
            return;
        }
        if (n == 2 && num[1] == num[0]) {
            System.out.println(num[1]);
            return;
        }
        if (num[1] - num[0] == 0) {
            a = 1;
            b = 0;
        } else {
            a = (num[2] - num[1]) / (num[1] - num[0]);
            b = num[1] - num[0] * a;
        }
        
        for (int i = 1; i < n; i++) {
            if (num[i] != (num[i - 1] * a + b)) {
                System.out.println("B");
                return;
            }
        }
        System.out.println(num[n - 1] * a + b);
    }
}