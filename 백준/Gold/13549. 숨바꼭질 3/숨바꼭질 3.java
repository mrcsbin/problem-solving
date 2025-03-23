import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] dist;

    public static void main(String[] args) throws Exception {
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
        Deque<Integer> deque = new ArrayDeque<>();
        dist[n] = 0;
        deque.offer(n);
        while (!deque.isEmpty()) {
            int now = deque.poll();

            if (isIn(now * 2) && dist[now * 2] > dist[now]) {
                dist[now * 2] = dist[now];
                deque.offerFirst(now * 2);
            }

            if (isIn(now + 1) && dist[now + 1] > dist[now] + 1) {
                dist[now + 1] = dist[now] + 1;
                deque.offerLast(now + 1);
            }

            if (isIn(now - 1) && dist[now - 1] > dist[now] + 1) {
                dist[now - 1] = dist[now] + 1;
                deque.offerLast(now - 1);
            }
        }
    }

    private static boolean isIn(int x) {
        return x >= 0 && x <= 100000;
    }
}