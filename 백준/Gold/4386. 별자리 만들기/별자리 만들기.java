import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static double answer;
    static int[] p;
    static Node[] nodes;
    static List<Edge> eList;

    static class Node {
        double x;
        double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int from;
        int to;
        double length;

        Edge(int from, int to, double length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nodes = new Node[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        eList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                eList.add(new Edge(i, j, getDistance(nodes[i], nodes[j])));
            }
        }

        kruskal();

        System.out.printf("%.2f", answer);
    }

    private static double getDistance(Node node1, Node node2) {
        return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node1.y - node2.y, 2));
    }

    private static void kruskal() {
        int count = 0;
        makeSet();
        eList.sort((o1, o2) -> Double.compare(o1.length, o2.length));
        for (Edge edge : eList) {
            if (!union(edge.from, edge.to)) continue;
            answer += edge.length;
            if (++count == n - 1) break;
        }
    }

    private static void makeSet() {
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    private static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        p[b] = a;
        return true;
    }
}