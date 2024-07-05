import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static int[] p;
    static List<int[]> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{u, v, w});
        }
        kruskal();
        System.out.println(answer);
    }

    private static void kruskal() {
        makeSet();
        int count = 0;
        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        int max = Integer.MIN_VALUE;
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) continue;
            answer += edge[2];
            max = Math.max(max, edge[2]);
            if (++count == n - 1) break;
        }
        answer -= max;
    }

    private static void makeSet() {
        p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if (a == p[a]) return a;
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