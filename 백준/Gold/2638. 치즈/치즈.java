import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static List<Cheese> cheeses;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Cheese {
        int x;
        int y;
        int outsideAirCount;

        Cheese(int x, int y) {
            this.x = x;
            this.y = y;
            getOutsideAirCount();
        }

        void getOutsideAirCount() {
            int outsideAirCount = 0;
            for (int d = 0; d < 4; d++) {
                int nx = this.x + dx[d];
                int ny = this.y + dy[d];
                if (map[nx][ny] == -1) outsideAirCount++;
            }

            this.outsideAirCount = outsideAirCount;
        }

        void spreadAir() {
            findOutsideAir(this.x, this.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        findOutsideAir(0, 0);

        cheeses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    cheeses.add(new Cheese(i, j));
                }
            }
        }

        int time = 0;
        while (!cheeses.isEmpty()) {
            time++;

            cheeses.sort((o1, o2) -> o2.outsideAirCount - o1.outsideAirCount);
            for (int i = 0; i < cheeses.size(); i++) {
                Cheese cheese = cheeses.get(i);
                if (cheese.outsideAirCount >= 2) {
                    cheese.spreadAir();
                    cheeses.remove(i--);
                    continue;
                }
                cheese.getOutsideAirCount();
            }
        }

        System.out.println(time);
    }

    private static void findOutsideAir(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        map[x][y] = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            x = current[0];
            y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isIn(nx, ny) && map[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    map[nx][ny] = -1;
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}