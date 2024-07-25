import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map, dist;
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
        dijkstra();
        System.out.println(dist[n - 1][m - 1]);
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[]{0, 0, 0});
        dist[0][0] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            if (dist[cur[0]][cur[1]] < cur[2]) continue;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isIn(nx, ny)) {
                    if (map[nx][ny] == 1 && dist[nx][ny] > dist[x][y] + 1) {
                        dist[nx][ny] = dist[x][y] + 1;
                        pq.offer(new int[]{nx, ny, d + 1});
                    } else if (map[nx][ny] == 0 && dist[nx][ny] > dist[x][y]) {
                        dist[nx][ny] = dist[x][y];
                        pq.offer(new int[]{nx, ny, d});
                    }
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}