import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, p;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] mCounts;
    static int[] answer;
    static char[][] map;
    static Queue<int[]>[] queueArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        mCounts = new int[p + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++) {
            mCounts[i] = Integer.parseInt(st.nextToken());
        }

        map = new char[n][];
        queueArr = new ArrayDeque[p + 1];
        for (int i = 1; i <= p; i++) {
            queueArr[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '.' && map[i][j] != '#') {
                    queueArr[map[i][j] - '0'].add(new int[]{i, j});
                }
            }
        }

        bfs();

        answer = new int[p + 1];
        getCount();

        for (int i = 1; i <= p; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static void bfs() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i <= p; i++) {
                int mCount = mCounts[i];
                while (mCount-- > 0 && !queueArr[i].isEmpty()) {
                    for (int j = 0, size = queueArr[i].size(); j < size; j++) {
                        int[] cur = queueArr[i].poll();
                        int x = cur[0];
                        int y = cur[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            if (isIn(nx, ny) && map[nx][ny] == '.') {
                                map[nx][ny] = (char) (i + '0');
                                queueArr[i].add(new int[]{nx, ny});
                                flag = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static void getCount() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '.' && map[i][j] != '#') {
                    answer[map[i][j] - '0']++;
                }
            }
        }
    }
}