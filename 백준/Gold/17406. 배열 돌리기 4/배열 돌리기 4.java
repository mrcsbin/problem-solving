import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, answer = Integer.MAX_VALUE;
    static int[][] map;
    static Rotation[] rotations;
    static Rotation[] select;
    static boolean[] isSelected;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Rotation {
        int r;
        int c;
        int s;

        Rotation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static void rotateMap(int[][] map, int r, int c, int s) {
        int startRow = r - s - 1;
        int startCol = c - s - 1;
        int endRow = r + s - 1;
        int endCol = c + s - 1;
        for (int i = 0; i < s; i++) {
            int x = startRow + i;
            int y = startCol + i;
            int dir = 0;
            int temp = map[x][y];
            while (dir < 4) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (isIn(nx, ny, startRow + i, startCol + i, endRow - i, endCol - i)) {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                } else {
                    dir++;
                }
            }
            map[startRow + i][startCol + i + 1] = temp;
        }
    }

    static boolean isIn(int x, int y, int startRow, int startCol, int endRow, int endCol) {
        return x >= startRow && y >= startCol && x <= endRow && y <= endCol;
    }

    static int calculateRowSum(int[][] map) {
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += map[i][j];
            }
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }

    static int[][] cloneMap() {
        int[][] clonedMap = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            clonedMap[i] = map[i].clone();
        }
        return clonedMap;
    }

    static void perm(int depth) {
        if (depth == k) {
            int[][] tempMap = cloneMap();
            for (Rotation rotation : select) {
                rotateMap(tempMap, rotation.r, rotation.c, rotation.s);
            }
            answer = Math.min(answer, calculateRowSum(tempMap));
            return;
        }

        for (int i = 0; i < k; i++) {
            if (isSelected[i]) continue;
            select[depth] = rotations[i];
            isSelected[i] = true;
            perm(depth + 1);
            isSelected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        rotations = new Rotation[k];
        select = new Rotation[k];
        isSelected = new boolean[k];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < k; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rotations[i] = new Rotation(input[0], input[1], input[2]);
        }
        perm(0);
        System.out.println(answer);
    }
}