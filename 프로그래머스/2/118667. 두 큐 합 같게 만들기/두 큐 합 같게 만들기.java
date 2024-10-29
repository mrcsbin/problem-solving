import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0, sum2 = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        long target = (sum1 + sum2) / 2;
        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }

        int limit = queue1.length * 3;
        int moves = 0;

        while (moves < limit) {
            if (sum1 == target) {
                return moves;
            }
            
            moves++;
            if (sum1 > sum2) {
                int poll = q1.poll();
                q2.offer(poll);
                sum1 -= poll;
                sum2 += poll;
            } else {
                int poll = q2.poll();
                q1.offer(poll);
                sum1 += poll;
                sum2 -= poll;
            }
        }

        return -1;
    }
}
