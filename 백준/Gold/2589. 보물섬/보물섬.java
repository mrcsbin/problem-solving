import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, dist;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'L') {
                    findDistance(i, j);
                }
            }
        }

        System.out.println(dist);
    }

    private static void findDistance(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});
        boolean[][] visited = new boolean[r][c];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            x = current[0];
            y = current[1];
            dist = Math.max(dist, current[2]);

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isIn(nx, ny) && map[nx][ny] == 'L' && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, current[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < r && y < c;
    }
}