import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        findMaxNumber(0, map);
        System.out.println(answer);
    }

    private static void findMaxNumber(int depth, int[][] map) {
        if (depth == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, map[i][j]);
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            rotateMap(map);
            int[][] tempMap = cloneMap(map);
            move(tempMap);
            findMaxNumber(depth + 1, tempMap);
        }
    }

    private static int[][] cloneMap(int[][] map) {
        int[][] tempMap = new int[n][];
        for (int i = 0; i < n; i++) {
            tempMap[i] = map[i].clone();
        }
        return tempMap;
    }

    private static void move(int[][] map) {
        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            int index = 0;
            boolean merged = false;
            for (int j = 0; j < n; j++) {
                if (map[j][i] != 0) {
                    if (index > 0 && temp[index - 1] == map[j][i] && !merged) {
                        temp[index - 1] *= 2;
                        merged = true;
                    } else {
                        temp[index++] = map[j][i];
                        merged = false;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                map[j][i] = temp[j];
            }
        }
    }

    private static void rotateMap(int[][] map) {
        int[][] tempMap = cloneMap(map);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = tempMap[n - j - 1][i];
            }
        }
    }
}