import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        broken = new boolean[10];
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int answer = Math.abs(n - 100);
        for (int i = 0; i < 1000000; i++) {
            String str = String.valueOf(i);
            boolean isValid = true;
            for (char ch : str.toCharArray()) {
                if (broken[ch - '0']) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                int count = str.length() + Math.abs(i - n);
                answer = Math.min(answer, count);
            }
        }

        System.out.println(answer);
    }
}
