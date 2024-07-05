import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int m, n, answer;
    static int[] p;
    static List<int[]> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) break;
            edges = new ArrayList<>();
            int sum = 0;
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges.add(new int[]{x, y, z});
                edges.add(new int[]{y, x, z});
                sum += z;
            }
            kruskal();
            sb.append(sum - answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void kruskal() {
        makeSet();
        int count = 0;
        answer = 0;
        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) continue;
            answer += edge[2];
            if (++count == n - 1) break;
        }
    }

    private static void makeSet() {
        p = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if (p[a] == a) return a;
        return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        p[b] = a;
        return true;
    }
}