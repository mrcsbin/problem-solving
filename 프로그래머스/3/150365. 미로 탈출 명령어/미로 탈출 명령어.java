import java.util.PriorityQueue;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'u', 'd', 'l', 'r'};

    class Node {
        int x;
        int y;
        String route;
        int count;

        Node(int x, int y, String route, int count) {
            this.x = x;
            this.y = y;
            this.route = route;
            this.count = count;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if ((k - dist) % 2 == 1 || k < dist) {
            return "impossible";
        } 
        String answer = bfs(n, m, x, y, r, c, k);
        return answer;
    }

    private String bfs(int n, int m, int x, int y, int r, int c, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.route.compareTo(o2.route));
        pq.offer(new Node(x, y, "", 0));
        boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
        visited[x][y][0] = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.count == k) {
                if (cur.x == r && cur.y == c) return cur.route;
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (isIn(nx, ny, n, m) && !visited[nx][ny][cur.count + 1]) {
                    visited[nx][ny][cur.count + 1] = true;
                    pq.offer(new Node(nx, ny, cur.route + dir[d], cur.count + 1));
                }
            }
        }
        
        return "impossible";
    }

    private boolean isIn(int x, int y, int n, int m) {
        return x >= 1 && y >= 1 && x <= n && y <= m;
    }
}