import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k, answer;
    static int[] p;
    static List<Edge> eList;
    static Set<Integer> hs;

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

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        eList = new ArrayList<>();
        hs = new HashSet<>();
        answer = 0;

        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {
            hs.add(Integer.parseInt(st.nextToken()));
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            eList.add(new Edge(from, to, cost));
        }

        kruskal();

        System.out.println(answer);
    }

    private static void makeSet() {
        p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }

        int[] plants = hs.stream().mapToInt(i -> i).toArray();
        for (int i = 1; i < plants.length; i++) {
            union(plants[0], plants[i]);
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

    private static void kruskal() {
        makeSet();
        eList.sort(Comparator.comparingInt(o -> o.cost));

        for (Edge edge : eList) {
            if (union(edge.from, edge.to)) {
                answer += edge.cost;

                int root = find(edge.from);
                if (hs.contains(root)) {
                    if (--n == hs.size()) {
                        break;
                    }
                }
            }
        }
    }
}