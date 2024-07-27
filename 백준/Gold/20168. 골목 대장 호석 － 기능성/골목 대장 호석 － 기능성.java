import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, a, b, c;
    static List<Node>[] graph;

    static class Node implements Comparable<Node> {
        int idx;
        int cost;
        int maxEdge;

        public Node(int idx, int cost, int maxEdge) {
            this.idx = idx;
            this.cost = cost;
            this.maxEdge = maxEdge;
        }

        public int compareTo(Node other) {
            return this.maxEdge - other.maxEdge;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, cost, cost));
            graph[v].add(new Node(u, cost, cost));
        }

        int result = dijkstra();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(a, 0, 0));
        int[] minShame = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            minShame[i] = Integer.MAX_VALUE;
        }
        minShame[a] = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currIdx = curr.idx;
            int currCost = curr.cost;
            int currShame = curr.maxEdge;
            if (currIdx == b) return currShame;
            for (Node next : graph[currIdx]) {
                int nextIdx = next.idx;
                int nextCost = currCost + next.cost;
                int nextShame = Math.max(currShame, next.cost);
                if (nextCost <= c && nextShame < minShame[nextIdx]) {
                    minShame[nextIdx] = nextShame;
                    pq.offer(new Node(nextIdx, nextCost, nextShame));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}