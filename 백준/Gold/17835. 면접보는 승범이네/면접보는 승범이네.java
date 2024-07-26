import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static long[] dist;
    static List<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int idx;
        long w;

        public Node(int idx, long w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Node other) {
            return Long.compare(this.w, other.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[v].add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int s = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s, 0));
            dist[s] = 0;
        }

        dijkstra();

        long maxDist = Long.MIN_VALUE;
        int maxIdx = 0;
        for (int i = 1; i <= n; i++) {
            if (maxDist < dist[i]) {
                maxDist = dist[i];
                maxIdx = i;
            }
        }
        System.out.println(maxIdx);
        System.out.println(maxDist);
    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist[curNode.idx] < curNode.w) continue;
            for (Node nextNode : graph[curNode.idx]) {
                long cost = dist[curNode.idx] + nextNode.w;
                if (dist[nextNode.idx] > cost) {
                    dist[nextNode.idx] = cost;
                    pq.offer(new Node(nextNode.idx, cost));
                }
            }
        }
    }
}