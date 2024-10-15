import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[][] space, compressed;
    static Map<Integer, Integer> rankMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        space = new int[m][n];
        compressed = new int[m][n];

        for (int i = 0; i < m; i++) {
            space[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            compressed[i] = compress(space[i]);
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (Arrays.equals(compressed[i], compressed[j])) answer++;
            }
        }
        System.out.println(answer);
    }

    private static int[] compress(int[] arr) {
        int[] sorted = Arrays.stream(arr.clone()).sorted().toArray();
        rankMap = new HashMap<>();

        int idx = 0;
        for (int value : sorted) {
            rankMap.put(value, idx++);
        }

        for (int i = 0; i < n; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        return arr;
    }
}