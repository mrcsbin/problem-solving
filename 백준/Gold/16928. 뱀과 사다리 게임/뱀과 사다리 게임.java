import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[101];

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        int minCount = bfs();

        System.out.println(minCount);
    }

    private static int bfs() {
        boolean[] visited = new boolean[101];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int num = current[0];
            int count = current[1];

            if (num == 100) return count;
            if (arr[num] != 0) num = arr[num];

            for (int i = 1; i <= 6; i++) {
                if (num + i <= 100 && !visited[num + i]) {
                    visited[num + i] = true;
                    queue.offer(new int[]{num + i, count + 1});
                }
            }
        }

        return -1;
    }
}