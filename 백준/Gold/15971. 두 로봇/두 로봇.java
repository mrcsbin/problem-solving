import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, a, b;
    static List<int[]>[] graph;

    private static int search() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{a, 0, 0});
        boolean[] visited = new boolean[n + 1];
        visited[a] = true;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int dist = current[1];
            int maxWeight = current[2];
            if (currentNode == b) return dist - maxWeight;
            for (int[] next : graph[current[0]]) {
                if (!visited[next[0]]) {
                    visited[next[0]] = true;
                    pq.add(new int[]{next[0], dist + next[1], Math.max(maxWeight, next[1])});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new int[]{to, cost});
            graph[to].add(new int[]{from, cost});
        }

        System.out.println(search());
    }
}