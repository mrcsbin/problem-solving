import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, s, d;
    static int[] dist;
    static List<Edge>[] graph, reverseGraph;
    static PriorityQueue<Edge> pq;
    static boolean[][] isPath;

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

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n];
            reverseGraph = new ArrayList[n];
            isPath = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                reverseGraph[i] = new ArrayList<>();
            }

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[from].add(new Edge(to, cost));
                reverseGraph[to].add(new Edge(from, cost));
            }

            dijkstra(false);
            markPath();
            dijkstra(true);

            System.out.println(dist[d] == Integer.MAX_VALUE ? -1 : dist[d]);
        }
    }

    private static void dijkstra(boolean ignoreShortestPath) {
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Edge curE = pq.poll();

            if (dist[curE.to] < curE.cost) continue;

            for (Edge nextE : graph[curE.to]) {
                if (ignoreShortestPath && isPath[curE.to][nextE.to]) {
                    continue;
                }

                int cost = dist[curE.to] + nextE.cost;
                if (dist[nextE.to] > cost) {
                    dist[nextE.to] = cost;
                    pq.offer(new Edge(nextE.to, cost));
                }
            }
        }
    }

    private static void markPath() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(d);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Edge prevE : reverseGraph[cur]) {
                if (dist[prevE.to] + prevE.cost == dist[cur]) {
                    if (!isPath[prevE.to][cur]) {
                        isPath[prevE.to][cur] = true;
                        queue.offer(prevE.to);
                    }
                }
            }
        }
    }
}