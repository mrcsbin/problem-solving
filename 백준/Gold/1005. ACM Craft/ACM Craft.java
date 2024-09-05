import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int t, n, k;
    static int[] in, a, times;
    static PriorityQueue<int[]> pq;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            a = new int[n + 1];
            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(input[i - 1]);
            }

            in = new int[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                in[e]++;
            }

            pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            for (int i = 1; i <= n; i++) {
                if (in[i] == 0) pq.offer(new int[]{i, a[i]});
            }

            times = new int[n + 1];
            topologySort();

            int target = Integer.parseInt(br.readLine());
            sb.append(times[target]).append("\n");
        }
        System.out.println(sb);
    }

    private static void topologySort() {
        while (!pq.isEmpty()) {
            int[] curE = pq.poll();
            times[curE[0]] = curE[1];
            for (int next : graph[curE[0]]) {
                if (--in[next] == 0) {
                    pq.offer(new int[]{next, times[curE[0]] + a[next]});
                }
            }
        }
    }
}