import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        bfs();

        System.out.println(dist[k]);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{n, 0});
        dist[n] = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == k) return;

            int[][] nextPositions = {{current[0] + 1, current[1] + 1}, {current[0] - 1, current[1] + 1}, {current[0] * 2, current[1]}};

            for (int[] next : nextPositions) {
                if (isIn(next[0]) && dist[next[0]] > next[1]) {
                    queue.offer(new int[]{next[0], next[1]});
                    dist[next[0]] = next[1];
                }
            }
        }
    }

    private static boolean isIn(int x) {
        return x >= 0 && x <= 100000;
    }
}