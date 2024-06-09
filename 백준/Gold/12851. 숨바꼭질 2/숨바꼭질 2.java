import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k, time, answer;
    static boolean[] visited;
    static int[] dx = {1, -1, 2};

    private static void bfs(int start, int target) {
        visited[start] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int loc = current[0];
            int dist = current[1];
            visited[loc] = true;
            if (loc == target) {
                if (time > dist) {
                    time = dist;
                    answer = 1;
                } else if (time == dist) {
                    answer++;
                }
            }
            for (int i = 0; i < 3; i++) {
                int nextLoc = loc + dx[i];
                if (i == 2) nextLoc = loc * dx[i];
                if (isValid(nextLoc) && !visited[nextLoc]) {
                    queue.offer(new int[]{nextLoc, dist + 1});
                }
            }
        }
    }

    private static boolean isValid(int loc) {
        return loc >= 0 && loc < k * 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = Integer.MAX_VALUE;
        visited = new boolean[k * 2];
        if (n >= k) {
            System.out.println(n - k + " " + 1);
            return;
        }
        bfs(n, k);
        System.out.println(time + " " + answer);
    }
}