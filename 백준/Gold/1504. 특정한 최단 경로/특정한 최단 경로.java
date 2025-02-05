import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, e;
    static List<Node>[] graph;

    static class Node {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist = dijkstra(1);
        int[] v1Dist = dijkstra(v1);
        int[] v2Dist = dijkstra(v2);

        int d1 = dist[v1] + v1Dist[v2] + v2Dist[n];
        int d2 = dist[v2] + v2Dist[v1] + v1Dist[n];

        int minCost = Math.min(d1, d2);

        System.out.println(minCost <= 0 || minCost >= 1_000_000_000 ? -1 : minCost);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.idx] < now.cost) continue;

            for (Node next : graph[now.idx]) {
                int nCost = now.cost + next.cost;
                if (dist[next.idx] > nCost) {
                    dist[next.idx] = nCost;
                    pq.offer(new Node(next.idx, nCost));
                }
            }
        }

        return dist;
    }
}