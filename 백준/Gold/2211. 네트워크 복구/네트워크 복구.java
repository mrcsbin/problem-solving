import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dist;
    static int[] come;
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
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        come = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w));
            graph[e].add(new Node(s, w));
        }

        dijkstra();
        StringBuilder sb = new StringBuilder();
        sb.append(n - 1).append("\n");
        for (int i = 2; i <= n; i++) {
            sb.append(i).append(" ").append(come[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist[curNode.idx] < curNode.w) continue;
            for (Node nextNode : graph[curNode.idx]) {
                int cost = dist[curNode.idx] + nextNode.w;
                if (dist[nextNode.idx] > cost) {
                    dist[nextNode.idx] = cost;
                    pq.offer(new Node(nextNode.idx, cost));
                    come[nextNode.idx] = curNode.idx;
                }
            }
        }
    }
}