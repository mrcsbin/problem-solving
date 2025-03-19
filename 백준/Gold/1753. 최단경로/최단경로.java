import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int v, e, k;
    static int[] dist;
    static List<Node>[] graph;

    static class Node {
        int idx;
        int distance;

        Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        dist = new int[v + 1];
        graph = new ArrayList[v + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        dijkstra(k);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.idx] < now.distance) continue;

            for (Node next : graph[now.idx]) {
                int nDist = dist[now.idx] + next.distance;
                if (dist[next.idx] > nDist) {
                    dist[next.idx] = nDist;
                    pq.offer(new Node(next.idx, nDist));
                }
            }
        }
    }
}