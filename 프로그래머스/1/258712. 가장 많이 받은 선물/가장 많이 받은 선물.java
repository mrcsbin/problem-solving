import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> fMap = new HashMap<>();
        int[] gPower = new int[friends.length];
        int[][] board = new int[friends.length][friends.length];
        int[] gCounts = new int[friends.length];
        
        for (int i = 0; i < friends.length; i++) {
            fMap.put(friends[i], i);
        }
        
        for (int i = 0; i < gifts.length; i++) {
            String A = gifts[i].split(" ")[0];
            String B = gifts[i].split(" ")[1];
            gPower[fMap.get(A)]++;
            gPower[fMap.get(B)]--;
            board[fMap.get(A)][fMap.get(B)]++;
        }
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (i == j ) continue;
                if (board[i][j] > board[j][i]) {
                    gCounts[i]++;
                } else if (board[i][j] < board[j][i]) {
                    gCounts[j]++;
                } else {
                    if (gPower[fMap.get(friends[i])] > gPower[fMap.get(friends[j])]) {
                        gCounts[i]++;
                    } else if (gPower[fMap.get(friends[i])] < gPower[fMap.get(friends[j])]) {
                        gCounts[j]++;
                    }
                }
            }
        }
        
        int answer = Arrays.stream(gCounts).max().getAsInt();
        return answer;
    }
}