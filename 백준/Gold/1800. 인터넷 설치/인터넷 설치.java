import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, p, k;
    static List<Node>[] graph;

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        while (p-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        int left = 0;
        int right = 1_000_000;
        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dijkstra(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean dijkstra(int x) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist[curNode.to] < curNode.cost) continue;
            for (Node nextNode : graph[curNode.to]) {
                int cost = dist[curNode.to] + (nextNode.cost > x ? 1 : 0);
                if (dist[nextNode.to] > cost) {
                    dist[nextNode.to] = cost;
                    pq.offer(new Node(nextNode.to, cost));
                }
            }
        }
        return dist[n] <= k;
    }
}