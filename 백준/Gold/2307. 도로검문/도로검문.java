import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dist, come;
    static List<Node>[] graph;

    static class Node implements Comparable<Node> {
        int idx;
        int w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Node other) {
            return this.w - other.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        graph = new ArrayList[n + 1];
        come = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w));
            graph[e].add(new Node(s, w));
        }

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

        int defaultTime = dist[n];

        int s = come[n];
        int e = n;
        int answer = 0;
        while (true) {
            dist = dijkstra(s, e);
            answer = Math.max(answer, dist[n]);
            if (come[s] == 0) break;
            s = come[s];
            e = come[e];
        }
        System.out.println(answer - defaultTime > 987_654_321 ? -1 : answer - defaultTime);
    }

    private static int[] dijkstra(int s, int e) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (dist[curNode.idx] < curNode.w) continue;
            for (Node nextNode : graph[curNode.idx]) {
                if (curNode.idx == s && nextNode.idx == e) continue;
                int cost = dist[curNode.idx] + nextNode.w;
                if (dist[nextNode.idx] > cost) {
                    dist[nextNode.idx] = cost;
                    pq.offer(new Node(nextNode.idx, cost));
                }
            }
        }
        return dist;
    }
}