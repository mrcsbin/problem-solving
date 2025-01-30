import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int v, fNode, fCost;
    static boolean[] visited;
    static List<Edge>[] tree;

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        tree = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;

                int cost = Integer.parseInt(st.nextToken());
                addEdge(from, to, cost);
            }
        }

        visited = new boolean[v + 1];
        dfs(1, 0);

        visited = new boolean[v + 1];
        dfs(fNode, 0);

        System.out.println(fCost);
    }

    private static void addEdge(int from, int to, int cost) {
        tree[from].add(new Edge(to, cost));
        tree[to].add(new Edge(from, cost));
    }

    private static void dfs(int now, int cost) {
        visited[now] = true;

        if (fCost < cost) {
            fCost = cost;
            fNode = now;
        }

        for (Edge edge : tree[now]) {
            if (!visited[edge.to]) {
                dfs(edge.to, cost + edge.cost);
            }
        }
    }
}