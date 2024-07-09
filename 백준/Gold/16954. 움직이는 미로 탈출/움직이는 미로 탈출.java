import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1, 0};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{7, 0});
        while (!queue.isEmpty()) {
            visited = new boolean[8][8];
            for (int i = 0, size = queue.size(); i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                if (map[x][y] == '#') continue;
                if (x == 0 && y == 7) return 1;
                for (int j = 0; j < 9; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            moveWall();
        }
        return 0;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    private static void moveWall() {
        for (int i = 7; i >= 0; i--) {
            if (i == 0) {
                Arrays.fill(map[i], '.');
                continue;
            }
            map[i] = map[i - 1].clone();
        }
    }
}