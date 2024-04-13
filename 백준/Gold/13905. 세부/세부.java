import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, loc[], answer;
    static PriorityQueue<int[]> pq;
    static List<int[]>[] graph;
    static boolean[] visited;

    static void prim() {
        pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        pq.offer(new int[]{loc[0], Integer.MAX_VALUE});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (visited[current[0]]) continue;
            visited[current[0]] = true;
            if (current[0] == loc[1]) {
                answer = current[1];
                break;
            }
            for (int[] next : graph[current[0]]) {
                if (!visited[next[0]]) {
                    pq.offer(new int[]{next[0], Math.min(current[1], next[1])});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        loc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new int[]{e, w});
            graph[e].add(new int[]{s, w});
        }
        answer = Integer.MAX_VALUE;
        prim();
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}