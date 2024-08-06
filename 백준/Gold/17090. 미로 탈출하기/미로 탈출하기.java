import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[][] isValid, visited;
    static char[][] map;
    static char[] dir = {'U', 'D', 'L', 'R'};
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isValid = new boolean[n][m];
        visited = new boolean[n][m];
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j)) {
                        answer++;
                    }
                } else if (isValid[i][j]) answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean dfs(int r, int c) {
        if (!isIn(r, c)) return true;
        if (visited[r][c]) return isValid[r][c];
        visited[r][c] = true;

        int idx = -1;
        for (int i = 0; i < 4; i++) {
            if (map[r][c] == dir[i]) {
                idx = i;
                break;
            }
        }
        int nr = r + dr[idx];
        int nc = c + dc[idx];

        isValid[r][c] = dfs(nr, nc);
        return isValid[r][c];
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }
}