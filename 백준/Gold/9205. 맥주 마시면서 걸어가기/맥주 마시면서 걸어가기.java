import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int n;
    static int[][] locations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            locations = new int[n + 2][2];

            for (int i = 0; i < n + 2; i++) {
                String[] input = br.readLine().split(" ");
                locations[i][0] = Integer.parseInt(input[0]);
                locations[i][1] = Integer.parseInt(input[1]);
            }

            System.out.println(bfs(locations, n) ? "happy" : "sad");
        }
    }

    static boolean bfs(int[][] locations, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 2];
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < n + 2; i++) {
                if (!visited[i] && isIn(locations[current], locations[i])) {
                    queue.add(i);
                    visited[i] = true;
                    if (i == n + 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean isIn(int[] p1, int[] p2) {
        int distance = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        return distance <= 1000;
    }
}