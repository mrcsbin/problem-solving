import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board;
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

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        board = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) sb.append("-").append(" ");
                else sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void dijkstra(int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();

            if (dist[curE.to] < curE.cost) continue;

            for (Edge nextE : graph[curE.to]) {
                int cost = dist[curE.to] + nextE.cost;
                if (dist[nextE.to] > cost) {
                    pq.offer(new Edge(nextE.to, cost));
                    dist[nextE.to] = cost;
                    if (s == curE.to) board[s][nextE.to] = nextE.to;
                    else board[s][nextE.to] = board[s][curE.to];
                }
            }
        }
    }
}