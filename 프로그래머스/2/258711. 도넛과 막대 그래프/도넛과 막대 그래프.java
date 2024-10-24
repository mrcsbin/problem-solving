import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }
        
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
        }
        
        int[] answer = new int[4];
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0 && out[i] >= 2) answer[0] = i;
            else if (in[i] >= 2 && out[i] == 2) answer[3]++;
            else if (in[i] >= 1 && out[i] == 0) answer[2]++;
        }
        
        answer[1] = out[answer[0]] - answer[3] - answer[2];
        return answer;
    }
}