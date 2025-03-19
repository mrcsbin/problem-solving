import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        int minDist = bfs();
        System.out.println(minDist);
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 1, 1});
        map[1][1] = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if (x == n && y == m) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isIn(nx, ny) && map[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny, dist + 1});
                    map[nx][ny] = -1;
                }
            }
        }

        return -1;
    }

    private static boolean isIn(int x, int y) {
        return x >= 1 && y >= 1 && x <= n && y <= m;
    }
}