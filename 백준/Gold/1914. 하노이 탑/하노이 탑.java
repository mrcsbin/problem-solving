import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static int n;
    static StringBuilder sb;

    private static void hanoi(int n, int from, int by, int to) {
        if (n == 0) return;
        hanoi(n - 1, from, to, by);
        sb.append(from + " " + to).append("\n");
        hanoi(n - 1, by, from, to);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        BigInteger bi = BigInteger.ONE.shiftLeft(n).subtract(BigInteger.ONE);
        System.out.println(bi);
        if (n <= 20) {
            hanoi(n, 1, 2, 3);
        }
        System.out.println(sb);
    }
}