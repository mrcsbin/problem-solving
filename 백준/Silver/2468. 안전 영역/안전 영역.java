import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int n, map[][];
    static boolean visited[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int x, int y, int h) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] > h) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][];
        visited = new boolean[n][n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }

        int answer = 0;
        for (int i = 0; i < max; i++) {
            visited = new boolean[n][n];
            int count = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!visited[j][k] && map[j][k] > i) {
                        bfs(j, k, i);
                        count++;
                    }
                }
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}