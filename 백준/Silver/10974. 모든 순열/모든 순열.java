import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] v;
    static int arr[];
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        v = new boolean[N];
        arr = new int[N];
        perm(0);
        System.out.println(sb.toString());
    }

    static void perm(int cnt) {
        if (cnt == N) {
            for (int a : arr) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!v[i]) {
                v[i] = true;
                arr[cnt] = i + 1;
                perm(cnt+1);
                v[i] = false;
            }
        }
    }
}
