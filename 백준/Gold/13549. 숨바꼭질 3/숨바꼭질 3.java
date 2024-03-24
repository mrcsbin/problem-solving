import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, dist[];
    static int[] dx = {-1, 1, 2};

    static void bfs(int x) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        dist[x] = 0;
        while (!queue.isEmpty()) {
            x = queue.poll();
            if (x == k) return;
            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                if (i == 2) {
                    nx = x * dx[i];
                    if (isIn(nx) && dist[nx] > dist[x]) {
                        dist[nx] = dist[x];
                        queue.offer(nx);
                    }
                    continue;
                }
                if (isIn(nx) && dist[nx] > dist[x] + 1) {
                    dist[nx] = dist[x] + 1;
                    queue.offer(nx);
                }
            }
        }
    }

    static boolean isIn(int x) {
        return x >= 0 && x <= 100_000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[100_001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        bfs(n);
        System.out.println(dist[k]);
    }
}