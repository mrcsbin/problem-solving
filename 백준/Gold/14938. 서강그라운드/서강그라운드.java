import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, r;
    static int[] items;
    static List<Edge>[] graph;
    static PriorityQueue<Edge> pq;

    static class Edge {
        int to;
        int length;

        Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        while (r-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        pq = new PriorityQueue<>((o1, o2) -> o1.length - o2.length);
        int maxScore = 0;
        for (int i = 1; i <= n; i++) {
            int[] dist = dijkstra(i);
            int score = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) score += items[j - 1];
            }
            maxScore = Math.max(maxScore, score);
        }
        System.out.println(maxScore);
    }

    private static int[] dijkstra(int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Edge(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();

            if (dist[curE.to] < curE.length) continue;

            for (Edge nextE : graph[curE.to]) {
                int nLength = dist[curE.to] + nextE.length;
                if (dist[nextE.to] > nLength) {
                    dist[nextE.to] = nLength;
                    pq.offer(new Edge(nextE.to, nLength));
                }
            }
        }
        return dist;
    }
}