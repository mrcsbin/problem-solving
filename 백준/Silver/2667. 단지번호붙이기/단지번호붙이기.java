import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) list.add(bfs(i, j));
            }
        }
        list.sort((o1, o2) -> o1 - o2);

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (int i : list) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        map[x][y] = 2;
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isIn(nx, ny) && map[nx][ny] == 1) {
                    map[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return count;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}