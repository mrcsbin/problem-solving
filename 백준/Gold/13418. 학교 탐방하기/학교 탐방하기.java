import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] p;
    static List<Edge> edges;

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        edges.add(new Edge(from, to, cost));

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        int best = kruskal(true);
        int worst = kruskal(false);
        System.out.println((int) (Math.pow(worst, 2) - Math.pow(best, 2)));
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

    private static int kruskal(boolean isBest) {
        makeSet();
        if (isBest) edges.sort((o1, o2) -> o2.cost - o1.cost);
        else edges.sort((o1, o2) -> o1.cost - o2.cost);

        int fatigue = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (!union(edge.from, edge.to)) continue;
            if (edge.cost == 0) fatigue++;
            if (++count == m) break;
        }
        return fatigue;
    }
}