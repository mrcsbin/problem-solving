import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dist, prev;
    static List<Edge>[] graph;

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        prev = new int[n + 1];
        dijkstra(-1, -1, true);

        List<int[]> eList = new ArrayList<>();
        int idx = n;
        while (idx != 0) {
            eList.add(new int[]{prev[idx], idx});
            idx = prev[idx];
        }

        int answer = Integer.MIN_VALUE;
        for (int[] edge : eList) {
            dijkstra(edge[0], edge[1], false);
            answer = Math.max(answer, dist[n]);
        }
        System.out.println(answer);
    }

    private static void dijkstra(int from, int to, boolean isFirst) {
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();

            if (dist[curE.to] < curE.cost) continue;

            for (Edge nextE : graph[curE.to]) {
                if ((from == curE.to && to == nextE.to) || (from == nextE.to && to == curE.to)) continue;
                int cost = dist[curE.to] + nextE.cost;
                if (dist[nextE.to] > cost) {
                    if (isFirst) prev[nextE.to] = curE.to;
                    dist[nextE.to] = cost;
                    pq.offer(new Edge(nextE.to, cost));
                }
            }
        }
    }
}