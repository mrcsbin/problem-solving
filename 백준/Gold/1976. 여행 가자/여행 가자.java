import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, graph[][], t[];
    static boolean visited[], find;

    static boolean dfs(int v, int t) {
        visited[v] = true;
        if (v == t) find = true;
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && graph[v][i] == 1) dfs(i, t);
            if (find) break;
        }
        return find;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        t = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean isValid = true;
        for (int i = 1; i < m; i++) {
            visited = new boolean[n + 1];
            find = false;
            if (!dfs(t[i - 1], t[i])) {
                isValid = false;
                break;
            }
        }
        System.out.println(isValid ? "YES" : "NO");
    }
}