import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[] dir = {'U', 'D', 'R', 'L'};
    static boolean[][] visited;
    static boolean[][] safeZone;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][];
        safeZone = new boolean[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) dfs(i, j);
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        int dir = getDir(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (!visited[nx][ny]) dfs(nx, ny);
        else if (!safeZone[nx][ny]) answer++;
        safeZone[x][y] = true;
    }

    private static int getDir(char ch) {
        for (int i = 0; i < 4; i++) {
            if (dir[i] == ch) return i;
        }
        return 0;
    }
}