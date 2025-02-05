import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int v, e, weight;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        kruskal();

        System.out.println(weight);
    }

    private static void kruskal() {
        make();
        Collections.sort(edges, (o1, o2) -> o1.cost - o2.cost);
        int count = 0;
        for (Edge edge : edges) {
            if (!union(edge.from, edge.to)) continue;
            weight += edge.cost;
            if (++count == v - 1) break;
        }
    }

    private static void make() {
        p = new int[v + 1];
        for (int i = 1; i <= v; i++) {
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