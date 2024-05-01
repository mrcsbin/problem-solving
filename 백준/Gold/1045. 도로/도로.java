import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, counts[], count, p[];
    static Queue<int[]> edges;

    static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return true;
        p[b] = a;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        counts = new int[n];
        p = new int[n];
        edges = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            char[] chars = br.readLine().toCharArray();
            for (int j = i + 1; j < n; ++j) {
                if (chars[j] == 'N') continue;
                if (!union(i, j)) {
                    count++;
                    counts[i]++;
                    counts[j]++;
                } else {
                    edges.add(new int[]{i, j});
                }
            }
        }
        if (count != n - 1) {
            System.out.println(-1);
            return;
        }
        for (int i = count; i < m; i++) {
            if (edges.isEmpty()) {
                System.out.println(-1);
                return;
            }
            int[] edge = edges.poll();
            counts[edge[0]]++;
            counts[edge[1]]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int count : counts) {
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }
}