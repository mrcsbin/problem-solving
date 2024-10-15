import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[][] space;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        space = new int[m][];

        for (int i = 0; i < m; i++) {
            space[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            compress(space[i]);
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (Arrays.equals(space[i], space[j])) answer++;
            }
        }
        
        System.out.println(answer);
    }

    private static void compress(int[] space) {
        int[] sorted = Arrays.stream(space.clone()).sorted().toArray();
        for (int i = 0; i < n; i++) {
            space[i] = Arrays.binarySearch(sorted, space[i]);
        }
    }
}