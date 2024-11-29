import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k, x;
    static int[] dist;
    static List<Edge>[] graph;

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, 1));
        }

        dijkstra();

        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) sb.append(i).append("\n");
        }

        System.out.println(sb.length() == 0 ? -1 : sb);
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dist[x] = 0;
        pq.offer(new Edge(x, 0));
        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();

            if (dist[curEdge.to] < curEdge.cost || curEdge.cost == k) continue;

            for (Edge nextEdge : graph[curEdge.to]) {
                int cost = dist[curEdge.to] + nextEdge.cost;
                if (dist[nextEdge.to] > cost) {
                    dist[nextEdge.to] = cost;
                    pq.offer(new Edge(nextEdge.to, cost));
                }
            }
        }
    }
}