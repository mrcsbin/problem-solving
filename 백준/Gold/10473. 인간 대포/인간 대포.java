import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static float x, y, tx, ty;
    static List<Edge>[] graph;
    static Location[] locations;
    static float[] times;

    static class Location {
        float x;
        float y;

        Location(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int idx;
        float w;

        Edge(int idx, float w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Edge other) {
            return Float.compare(this.w, other.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Float.parseFloat(st.nextToken());
        y = Float.parseFloat(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tx = Float.parseFloat(st.nextToken());
        ty = Float.parseFloat(st.nextToken());
        int n = Integer.parseInt(br.readLine());

        locations = new Location[n + 3];
        locations[1] = new Location(x, y);
        locations[n + 2] = new Location(tx, ty);

        graph = new ArrayList[n + 3];
        times = new float[n + 3];
        for (int i = 1; i < n + 3; i++) {
            graph[i] = new ArrayList<>();
            times[i] = Float.MAX_VALUE;
        }

        for (int i = 2; i < n + 2; i++) {
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            locations[i] = new Location(x, y);
        }

        for (int i = 1; i < n + 3; i++) {
            for (int j = 1; j < n + 3; j++) {
                if (i == j) continue;
                float dist = getDistance(locations[i], locations[j]);
                if (i == 1) graph[i].add(new Edge(j, dist / 5));
                else graph[i].add(new Edge(j, Math.min(dist / 5, Math.abs(dist - 50) / 5 + 2)));
            }
        }

        dijkstra();
        System.out.println(times[n + 2]);
    }

    private static float getDistance(Location location1, Location location2) {
        return (float) Math.sqrt(Math.pow(location1.x - location2.x, 2) + Math.pow(location1.y - location2.y, 2));
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        times[1] = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (times[cur.idx] < cur.w) continue;
            for (Edge next : graph[cur.idx]) {
                float dist = times[cur.idx] + next.w;
                if (times[next.idx] > dist) {
                    times[next.idx] = dist;
                    pq.offer(new Edge(next.idx, dist));
                }
            }
        }
    }
}