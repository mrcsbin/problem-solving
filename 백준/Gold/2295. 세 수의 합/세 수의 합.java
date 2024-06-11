import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] u = new int[n];
        for (int i = 0; i < n; i++) {
            u[i] = Integer.parseInt(br.readLine());
        }

        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                hs.add(u[i] + u[j]);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (hs.contains(u[i] - u[j])) answer = Math.max(answer, u[i]);
            }
        }
        System.out.println(answer);
    }
}