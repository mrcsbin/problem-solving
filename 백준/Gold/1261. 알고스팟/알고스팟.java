import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[n - 1][m - 1]);
    }

    private static void bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0});
        dist[0][0] = 0;
        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int x = current[0];
            int y = current[1];

            if (x == n - 1 && y == m - 1) return;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isIn(nx, ny)) {
                    int cost = map[nx][ny] == 0 ? 0 : 1;
                    if (dist[nx][ny] > dist[x][y] + cost) {
                        dist[nx][ny] = dist[x][y] + cost;
                        if (cost == 0) deque.offerFirst(new int[]{nx, ny});
                        else if (cost == 1) deque.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}