import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int count = nCr(m, n);

            System.out.println(count);
        }
    }

    private static int nCr(int n, int r) {
        int result = 1;

        for (int i = 1; i <= r; i++) {
            result *= n - i + 1;
            result /= i;
        }

        return result;
    }
}