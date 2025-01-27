import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map, tempMap;
    static boolean[][] isOutside;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        tempMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int time = -1;
        while (true) {
            time++;
            findOutsideAir();
            for (int i = 0; i < n; i++) {
                tempMap[i] = map[i].clone();
            }
            if (!melt()) break;
        }

        System.out.println(time);
    }

    private static void findOutsideAir() {
        Queue<int[]> queue = new ArrayDeque<>();
        isOutside = new boolean[n][m];
        queue.offer(new int[]{0, 0});
        isOutside[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isIn(nx, ny) && !isOutside[nx][ny] && map[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    isOutside[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static boolean melt() {
        boolean isCheeseLeft = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempMap[i][j] == 1) {
                    isCheeseLeft = true;
                    int outsideAir = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (isOutside[nx][ny]) outsideAir++;
                    }

                    if (outsideAir >= 2) map[i][j] = 0;
                }
            }
        }
        return isCheeseLeft;
    }
}