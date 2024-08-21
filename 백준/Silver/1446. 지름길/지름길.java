import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, d;
    static int[] dist;
    static List<Node>[] graph;

    static class Node implements Comparable<Node> {
        int idx;
        int w;

        public Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        dist = new int[d + 1];
        graph = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (s <= d && e <= d && e - s > w) {
                graph[s].add(new Node(e, w));
            }
        }

        for (int i = 0; i < d; i++) {
            graph[i].add(new Node(i + 1, 1));
        }
        dijkstra();
        System.out.println(dist[d]);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.w > dist[current.idx]) continue;
            for (Node next : graph[current.idx]) {
                int cost = dist[current.idx] + next.w;
                if (dist[next.idx] > cost) {
                    dist[next.idx] = cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}