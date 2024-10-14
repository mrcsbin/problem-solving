import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int t, n, m, k;
    static int[] dist, location;
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

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
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

            int k = Integer.parseInt(br.readLine());
            location = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int minSum = Integer.MAX_VALUE;
            int answer = 0;
            for (int i = 1; i <= n; i++) {
                dist = dijkstra(i);
                int sum = 0;
                for (int j = 0; j < k; j++) {
                    sum += dist[location[j]];
                }

                if (minSum > sum) {
                    minSum = sum;
                    answer = i;
                }
            }

            System.out.println(answer);
        }
    }

    private static int[] dijkstra(int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Edge curE = pq.poll();

            if (dist[curE.to] < curE.cost) continue;

            for (Edge nextE : graph[curE.to]) {
                if (dist[nextE.to] > dist[curE.to] + nextE.cost) {
                    dist[nextE.to] = dist[curE.to] + nextE.cost;
                    pq.offer(new Edge(nextE.to, dist[nextE.to]));
                }
            }
        }

        return dist;
    }
}