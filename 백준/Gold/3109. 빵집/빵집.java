import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static char[][] map;
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};
    static boolean flag, visited[][];

    static void dfs(int r, int c) {
        visited[r][c] = true;
        if (c == m - 1) {
            answer++;
            flag = true;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (flag) return;
            if (isIn(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
                dfs(nr, nc);
            }
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            flag = false;
            dfs(i, 0);
        }
        System.out.println(answer);
    }
}