import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int n;
    static boolean[] isPrime;
    static List<Integer> primes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        findPrimeNumber();

        int l = 0;
        int sum = 0;
        int count = 0;
        for (int r = 0; r < primes.size(); r++) {
            sum += primes.get(r);

            while (sum > n) {
                sum -= primes.get(l++);
            }

            if (sum == n) {
                count++;
                sum -= primes.get(l++);
            }
        }

        System.out.println(count);
    }

    private static void findPrimeNumber() {
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!isPrime[i]) continue;
            for (int j = i * 2; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
    }
}