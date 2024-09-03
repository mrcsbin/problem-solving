import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] p;
    static long answer;
    static List<Edge> eList;

    static class Location {
        int idx;
        int val;

        Location(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

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
        n = Integer.parseInt(br.readLine());

        List<Location> x = new ArrayList<>();
        List<Location> y = new ArrayList<>();
        List<Location> z = new ArrayList<>();
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x.add(new Location(i, Integer.parseInt(st.nextToken())));
            y.add(new Location(i, Integer.parseInt(st.nextToken())));
            z.add(new Location(i, Integer.parseInt(st.nextToken())));
        }

        x.sort((o1, o2) -> o1.val - o2.val);
        y.sort((o1, o2) -> o1.val - o2.val);
        z.sort((o1, o2) -> o1.val - o2.val);

        eList = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            eList.add(new Edge(x.get(i).idx, x.get(i + 1).idx, Math.abs(x.get(i + 1).val - x.get(i).val)));
            eList.add(new Edge(y.get(i).idx, y.get(i + 1).idx, Math.abs(y.get(i + 1).val - y.get(i).val)));
            eList.add(new Edge(z.get(i).idx, z.get(i + 1).idx, Math.abs(z.get(i + 1).val - z.get(i).val)));
        }

        kruskal();

        System.out.println(answer);
    }

    private static void kruskal() {
        makeSet();
        int count = 0;
        eList.sort((o1, o2) -> o1.cost - o2.cost);
        for (Edge edge : eList) {
            if (!union(edge.from, edge.to)) continue;
            answer += edge.cost;
            if (++count == n - 1) break;
        }
    }

    private static void makeSet() {
        p = new int[n];
        for (int i = 0; i < n; i++) {
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