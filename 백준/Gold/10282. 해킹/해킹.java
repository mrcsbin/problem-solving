import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, d, c, a, b, s;
    static List<int[]>[] list;
    static int[] dist;
    static boolean[] visited;

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        dist[c] = 0;
        pq.offer(new int[]{c, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            for (int[] next : list[cur[0]]) {
                if (dist[next[0]] > dist[cur[0]] + next[1]) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }
    }

    static int[] getCount() {
        int count = 0;
        int time = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                count++;
                time = Math.max(time, dist[i]);
            }
        }
        return new int[]{count, time};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list = new ArrayList[n + 1];
            dist = new int[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                dist[i] = Integer.MAX_VALUE;
                list[i] = new ArrayList<>();
            }
            while (d-- > 0) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                list[b].add(new int[]{a, s});
            }
            dijkstra();
            int[] answer = getCount();
            sb.append(answer[0] + " " + answer[1]).append("\n");
        }
        System.out.print(sb);
    }
}