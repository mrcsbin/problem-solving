import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, sx, sy;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][][] visited;

    static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0, 0});
        visited[0][sx][sy] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int d = current[2];
            int b = current[3];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isIn(nx, ny) && map[nx][ny] != '#' && !visited[b][nx][ny]) {
                    if (Character.isLowerCase(map[nx][ny])) {
                        visited[b | (1 << map[nx][ny] - 'A')][nx][ny] = true;
                        queue.offer(new int[]{nx, ny, d + 1, b | (1 << map[nx][ny] - 'A')});
                    } else if (Character.isUpperCase(map[nx][ny]) && (b & (1 << map[nx][ny] - 'A')) != 0) {
                        visited[b][nx][ny] = true;
                        queue.offer(new int[]{nx, ny, d + 1, b});
                    } else if (map[nx][ny] == '.') {
                        visited[b][nx][ny] = true;
                        queue.offer(new int[]{nx, ny, d + 1, b});
                    } else if (map[nx][ny] == '1') {
                        return d + 1;
                    }
                }
            }
        }
        return -1;
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][];
        visited = new boolean[1 << 6][n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0') {
                    sx = i;
                    sy = j;
                    map[i][j] = '.';
                }
            }
        }
        System.out.println(bfs());
    }
}