import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, w;
    static int[] dist;
    static List<Edge> edgeList;
    static boolean hasCycle;

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
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            dist = new int[n + 1];
            Arrays.fill(dist, (int) 1e9);

            edgeList = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(from, to, cost));
                edgeList.add(new Edge(to, from, cost));
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(from, to, -cost));
            }

            hasCycle = false;
            bellmanFord();
            System.out.println(hasCycle ? "YES" : "NO");
        }
    }

    private static void bellmanFord() {
        dist[1] = 0;

        for (int i = 0; i < n; i++) {
            for (Edge edge : edgeList) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                    if (i == n - 1) hasCycle = true;
                }
            }
        }
    }
}