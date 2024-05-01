import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        boolean[] isPath = new boolean[n];
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 100_000_000);
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
            }
        }
        
        for (int[] fare : fares) {
            dist[fare[0] - 1][fare[1] - 1] = fare[2];
            dist[fare[1] - 1][fare[0] - 1] = fare[2];
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, dist[s - 1][i] + dist[i][a - 1] + dist[i][b - 1]);
        }
        
        return answer;
    }
}