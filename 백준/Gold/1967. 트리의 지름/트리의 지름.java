import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, maxScore;
    static boolean[] visited;
    static List<Node>[] graph;

    static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            maxScore = Integer.MIN_VALUE;
            dfs(i, 0);
            answer = Math.max(answer, maxScore);
        }
        System.out.println(answer);
    }

    private static void dfs(int s, int score) {
        visited[s] = true;
        maxScore = Math.max(maxScore, score);
        for (Node next : graph[s]) {
            if (visited[next.to]) continue;
            dfs(next.to, score + next.cost);
        }
    }
}