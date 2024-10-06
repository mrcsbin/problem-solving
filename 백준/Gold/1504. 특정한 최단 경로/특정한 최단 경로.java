import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, E;
    static int v1, v2;
    static List<Edge>[] edges;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];

        for (int n = 0; n < N + 1; n++) {
            edges[n] = new ArrayList<>();
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        int from1to2 = dijk(v1, v2);
        int min = -1;

        if (from1to2 != -1) {
            int toV1 = dijk(1, v1);
            int toV2 = dijk(1, v2);

            if (toV1 != -1) {
                int fromV2 = dijk(v2, N);
                if (fromV2 != -1)
                    min = from1to2 + toV1 + fromV2;
            }

            if (toV2 != -1) {
                int fromV1 = dijk(v1, N);
                if (fromV1 != -1) {
                    if (min != -1)
                        min = Math.min(min, from1to2 + toV2 + fromV1);
                    else
                        min = from1to2 + toV2 + fromV1;
                }
            }
        }

        System.out.println(min);

    }

    static int dijk(int start, int end) {
        v = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (now.vertex == end) {
                return now.weight;
            }

            if (v[now.vertex])
                continue;
            v[now.vertex] = true;

            for (Edge e : edges[now.vertex]) {
                if (!v[e.vertex])
                    pq.add(new Edge(e.vertex, e.weight + now.weight));
            }
        }

        return -1;
    }
}
