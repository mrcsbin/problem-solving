import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] a;
    static long[] dist;
    static List<Node>[] graph;

    static class Node implements Comparable<Node> {
        int idx;
        long w;

        Node(int idx, long w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Node other) {
            return Long.compare(this.w, other.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        graph = new ArrayList[n];
        dist = new long[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if ((a[s] == 1 && s != n - 1) || (a[e] == 1 && e != n - 1)) continue;
            graph[s].add(new Node(e, w));
            graph[e].add(new Node(s, w));
        }

        dijkstra();
        System.out.println(dist[n - 1] == Long.MAX_VALUE ? -1 : dist[n - 1]);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;
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