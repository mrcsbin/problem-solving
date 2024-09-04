import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static long[] dist, answer;
    static List<Edge>[] graph;

    static class Edge {
        int to;
        long cost;

        Edge(int to, long cost) {
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
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        dijkstra();

        System.out.println(Arrays.stream(answer).sum());
    }

    private static void dijkstra() {
        answer = new long[n + 1];
        dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
        pq.offer(new Edge(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();

            if (dist[curE.to] < curE.cost) continue;

            for (Edge nextE : graph[curE.to]) {
                long cost = dist[curE.to] + nextE.cost;
                if (dist[nextE.to] > cost) {
                    answer[nextE.to] = (long) (dist[curE.to] * 0.9) + nextE.cost;
                    dist[nextE.to] = cost;
                    pq.offer(new Edge(nextE.to, cost));
                } else if (dist[nextE.to] == cost) {
                    answer[nextE.to] = Math.min(answer[nextE.to], (long) (dist[curE.to] * 0.9) + nextE.cost);
                }
            }
        }
    }
}