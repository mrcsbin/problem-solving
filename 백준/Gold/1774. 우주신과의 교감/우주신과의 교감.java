import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, p[];
    static List<Point> nodes;
    static List<Edge> edges;

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        double dist;

        Edge(int s, int e, double dist) {
            this.s = s;
            this.e = e;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    static void makeSet() {
        p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
    }

    static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return true;
        p[b] = a;
        return false;
    }

    static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        nodes.add(null);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.add(new Point(x, y));
        }

        makeSet();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s, e);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                edges.add(new Edge(i, j, getDistance(nodes.get(i), nodes.get(j))));
            }
        }

        Collections.sort(edges);
        double answer = 0.0;
        for (Edge edge : edges) {
            if (union(edge.s, edge.e)) continue;
            answer += edge.dist;
        }
        System.out.printf("%.2f", answer);
    }
}