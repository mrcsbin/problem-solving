import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n, k;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new boolean[m][n];

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    map[y][x] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int areaCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!map[i][j] && ++areaCount != 0) {
                    list.add(getAreaSize(j, i));
                }
            }
        }
        list.sort((o1, o2) -> o1 - o2);

        System.out.println(areaCount);
        for (int count : list) {
            System.out.print(count + " ");
        }
    }

    private static int getAreaSize(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        int areaSize = 0;
        map[y][x] = true;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            x = point[0];
            y = point[1];
            areaSize++;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isIn(nx, ny) && !map[ny][nx]) {
                    queue.offer(new int[]{nx, ny});
                    map[ny][nx] = true;
                }
            }
        }

        return areaSize;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}