import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, s, e;
    static int[] dist;
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
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dijkstra(s);

        System.out.println(dist[e]);
    }

    private static void dijkstra(int start) {
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
    }
}