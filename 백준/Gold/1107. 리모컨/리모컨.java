import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, mask, answer;

    private static int calculateMinCount(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((mask & (1 << str.charAt(i) - '0')) != 0) return Integer.MAX_VALUE;
        }
        return str.length() + Math.abs(n - Integer.parseInt(str));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                mask |= (1 << Integer.parseInt(st.nextToken()));
            }
        }

        answer = Math.abs(n - 100);
        for (int i = 0; i < 1000000; i++) {
            answer = Math.min(answer, calculateMinCount(String.valueOf(i)));
        }

        System.out.println(answer);
    }
}